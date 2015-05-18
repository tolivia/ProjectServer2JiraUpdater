package com.directv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.Status;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class Batch {

	/*Not needed since Jira priorities changed to be same as in EPMO: HP, P2, P3, P4
	 * Map<String,String> priorities = new HashMap<String,String>(){{
		put("HP","Critical");
		put("P1","High");
		put("P2","Medium");
		put("P3","Low");
	}};*/
	
    @Bean
    public ItemReader<Project> reader() {
        FlatFileItemReader<Project> reader = new FlatFileItemReader<Project>();
        reader.setResource(new ClassPathResource("ExecProj.csv"));
        reader.setLineMapper(new DefaultLineMapper<Project>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] {"wbs", "summary", "description",
            			"technicalLead", "programManager","projectManager",
            			"projectStartDate", "projectFinishDate","pdrDate","cdrDate", "inServiceDate", "keyRelease","priority","siteUrl"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Project>() {{
                setTargetType(Project.class);
            }});
        }});
        return reader;
    }

    
    @Bean
    public ItemProcessor<Project,Project> processor() {
    	return new ItemProcessor<Project,Project>(){
    	    @Override
    	    public Project process(final Project project) throws Exception {
    	    	//project.setDescription(project.getDescription() + project.getSiteUrl());
    	        project.setPriority(project.getPriority());
    	    	return project;
    	    }
    	};
    }
    
    @Bean
    public ItemWriter<Project> writer() {
        return new ItemWriter<Project>(){

			@Override
			public void write(List<? extends Project> items) throws Exception {
				BasicCredentials creds = new BasicCredentials("457172", "Tolivia000");
				JiraClient jira = new JiraClient("http://jirctsdv-msdc01.ds.dtveng.net:8080", creds);
				//BasicCredentials creds = new BasicCredentials("jira2psintegration", "Directv2015");
				//JiraClient jira = new JiraClient("http://jirapppr-labc01.ds.dtvops.net", creds);
				
				//creamos el bufer de salida
			    StringBuffer bufferExitos = new StringBuffer();
			    StringBuffer bufferFallos = new StringBuffer();
			    bufferExitos.append("*******Jira & Project Server Syncronizer*******");
			    bufferExitos.append("\n\r"); 
			    bufferExitos.append("[UPDATE STARTED]-START TIME: " + new Date() + " ");
			    bufferExitos.append("\n\r");
		        for(Project item : items){
					System.out.println("WBS=" + item.getWbs());
					if ((item.getWbs() != null) && !(item.getWbs().isEmpty())){
						Issue.SearchResult sr = jira.searchIssues("wbs ~ " + item.getWbs());
					    System.out.print(" (0) :: " + sr.total + " Projects with WBS= " + item.getWbs());
						  if (sr.issues.size() > 1){ 
							  System.out.println(" (1) :: Duplicate Entry for Project: " + item.getWbs());
							  bufferFallos.append(" " + sr.total + " Projects with WBS as : " + item.getWbs() + " ");
							  bufferFallos.append("\n\r");
							  bufferFallos.append("DUPLICATED :: Duplicate Entry for Project: " + item.getWbs() + " ");
							  bufferFallos.append("\n\r");
						  }
						  else if (sr.issues.size() == 0){ 
							  //Create new Project
							  System.out.println(" (2) :: Project Not Created in Jira: " + item.getWbs() + ": " + item.getSummary());
							  bufferFallos.append("[ERROR]-PROJECT NOT FOUND IN JIRA with WBS=" + item.getWbs() + ": " + item.getSummary() + " ");
							  bufferFallos.append("\n\r");
						  }
						  else{//Edit existing Project
							  final Issue issue = sr.issues.get(0);
							  System.out.println("STATUS " + issue.getStatus().getDescription());
					          if (!issue.getStatus().getDescription().equals("CLOSED"))
					          {
					        	  issue.update().field(Field.PRIORITY, item.getPriority()).execute();
					        	  System.out.println(" (3) :: Project " + issue.getKey() + " Updated to Priority: " + issue.getPriority());
					        	  bufferExitos.append("UPDATED :: Project " + issue.getKey() + " Updated to Priority: " + issue.getPriority());
					        	  bufferExitos.append("\n\r");
					          }
					          else{
					        	  bufferFallos.append("[ERROR]-PROJECT IS CLOSED=" + item.getWbs() + "-" + issue.getKey());
					        	  bufferFallos.append("\n\r");
					          }
						  }
					}
				    bufferFallos.append("[ERROR]-WBS FIELD EMPTY= " + item.getSummary());
				    bufferFallos.append("\n\r");
				}
			    bufferFallos.append("[UPDATE ENDED]-END TIME: " + new Date() + " ");
			    bufferFallos.append("\n\r");
				BufferedWriter writer = new BufferedWriter( new FileWriter("milog.log"));
				writer.write(bufferExitos.toString());
				writer.newLine();writer.newLine();
				writer.write(bufferFallos.toString());
				writer.newLine();writer.newLine();writer.newLine();
				writer.flush();
				writer.close();
			}
        };
    }

    @Bean
    public Job updateJira(JobBuilderFactory jobs, Step s1) {
        return jobs.get("updateJira")
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Project> reader,
            ItemWriter<Project> writer, ItemProcessor<Project,Project> processor) {
        return stepBuilderFactory.get("step1")
        		.allowStartIfComplete(true)
                .<Project, Project> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
