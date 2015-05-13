package com.directv;

import java.util.List;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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

    @Bean
    public ItemReader<Project> reader() {
        FlatFileItemReader<Project> reader = new FlatFileItemReader<Project>();
        reader.setResource(new ClassPathResource("ProjectInfoSharePoint_1.csv"));
        reader.setLineMapper(new DefaultLineMapper<Project>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "Finance_Charge_Code", "ProjectName", "Technical_Lead",	"Program_Manager",
                		"Project_Manager", "ProjectStartDate", "ProjectFinishDate",
                		"PDR_Finish", "CDR_Finish",	"Client_InService_Release",	"Key_Release", "Site_URL", "Priority"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Project>() {{
                setTargetType(Project.class);
            }});
        }});
        return reader;
    }

    
    @Bean
    public ItemWriter<Project> writer() {
        return new ItemWriter<Project>(){

			@Override
			public void write(List<? extends Project> items) throws Exception {
				for(Project item : items){
					System.out.println("Sending to JIRA "+item);
				}
				//BasicCredentials creds = new BasicCredentials("XXX", "atitelovoydicir");
				BasicCredentials creds = new BasicCredentials("elToli", "Oculta");
		        JiraClient jira = new JiraClient("http://jirctsdv-msdc01.ds.dtveng.net:8080", creds);
				Issue issue = jira.getIssue("TBD-696");
				issue.update().field(Field.SUMMARY, "Demo Description").execute();
	            issue.update().field(Field.PRIORITY, "Critical").execute();
				//.fieldRemove(Field.LABELS, "foo")
	            //.execute();
		        System.out.println(issue);
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
            ItemWriter<Project> writer) {
        return stepBuilderFactory.get("step1")
        		.allowStartIfComplete(true)
                .<Project, Project> chunk(10)
                .reader(reader)
                .writer(writer)
                .build();
    }


}
