package com.directv;


/*
 * Public class to encapsulate the Project object information coming from ProjectServer
 * and populating it after transformation to Jira Global Project
 */
public class Project {

	//information from ProjectServer site/report -- total 17 processed fields
	private String wbs; //Finance_Charge_Code
	private String summary; //ProjectName;
	private String epmosummary; //EPMO Summary; -- Not used 
	private String description;
	private String technicalLead;
	private String programManager;
	private String projectManager;
	private String projectStartDate;
	private String projectFinishDate;
	private String pdrDate; //PDR_Finish;
	private String cdrDate; //CDR_Finish;
	private String inServiceDate; //Client_InService_Release;
	private String keyRelease;
	private String priority;
	private String siteUrl;
	private String techLeadAccount;
	private String programMgrAccount;
	private String projectMgrAccount;
	private String prj;
	private String modifiedDate;
	

    public Project() {
    	
    }

	/**
	 * @param wbs
	 * @param summary
	 * @param epmosummary
	 * @param description
	 * @param technicalLead
	 * @param programManager
	 * @param projectManager
	 * @param projectStartDate
	 * @param projectFinishDate
	 * @param pdrDate
	 * @param cdrDate
	 * @param inServiceDate
	 * @param keyRelease
	 * @param priority
	 * @param siteUrl
	 * @param techLeadAccount
	 * @param programMgrAccount
	 * @param projectMgrAccount
	 * @param prj
	 */
	public Project(String wbs, String summary, String epmosummary,
			String description, String technicalLead, String programManager,
			String projectManager, String projectStartDate,
			String projectFinishDate, String pdrDate, String cdrDate,
			String inServiceDate, String keyRelease, String priority,
			String siteUrl, String techLeadAccount, String programMgrAccount,
			String projectMgrAccount, String prj) {
		super();
		this.wbs = wbs;
		this.summary = summary;
		this.epmosummary = epmosummary;
		this.description = description;
		this.technicalLead = technicalLead;
		this.programManager = programManager;
		this.projectManager = projectManager;
		this.projectStartDate = projectStartDate;
		this.projectFinishDate = projectFinishDate;
		this.pdrDate = pdrDate;
		this.cdrDate = cdrDate;
		this.inServiceDate = inServiceDate;
		this.keyRelease = keyRelease;
		this.priority = priority;
		this.siteUrl = siteUrl;
		this.techLeadAccount = techLeadAccount;
		this.programMgrAccount = programMgrAccount;
		this.projectMgrAccount = projectMgrAccount;
		this.prj = prj;
	}


	/**
	 * @return the wbs
	 */
	public String getWbs() {
		return wbs;
	}

	
	/**
	 * @param wbs the wbs to set
	 */
	public void setWbs(String wbs) {
		this.wbs = wbs;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the epmosummary
	 */
	/*
	public String getEpmosummary() {
		return epmosummary;
	}
	*/
	/**
	 * @param epmosummary the epmosummary to set
	 */
	/*public void setEpmosummary(String epmosummary) {
		this.epmosummary = epmosummary;
	}*/


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the technicalLead
	 */
	public String getTechnicalLead() {
		return technicalLead;
	}

	/**
	 * @param technicalLead the technicalLead to set
	 */
	public void setTechnicalLead(String technicalLead) {
		this.technicalLead = technicalLead;
	}

	/**
	 * @return the programManager
	 */
	public String getProgramManager() {
		return programManager;
	}

	/**
	 * @param programManager the programManager to set
	 */
	public void setProgramManager(String programManager) {
		this.programManager = programManager;
	}

	/**
	 * @return the projectManager
	 */
	public String getProjectManager() {
		return projectManager;
	}

	/**
	 * @param projectManager the projectManager to set
	 */
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	/**
	 * @return the projectStartDate
	 */
	public String getProjectStartDate() {
		return projectStartDate;
	}

	/**
	 * @param projectStartDate the projectStartDate to set
	 */
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	/**
	 * @return the projectFinishDate
	 */
	public String getProjectFinishDate() {
		return projectFinishDate;
	}

	/**
	 * @param projectFinishDate the projectFinishDate to set
	 */
	public void setProjectFinishDate(String projectFinishDate) {
		this.projectFinishDate = projectFinishDate;
	}

	/**
	 * @return the pdrDate
	 */
	public String getPdrDate() {
		return pdrDate;
	}

	/**
	 * @param pdrDate the pdrDate to set
	 */
	public void setPdrDate(String pdrDate) {
		this.pdrDate = pdrDate;
	}

	/**
	 * @return the cdrDate
	 */
	public String getCdrDate() {
		return cdrDate;
	}

	/**
	 * @param cdrDate the cdrDate to set
	 */
	public void setCdrDate(String cdrDate) {
		this.cdrDate = cdrDate;
	}

	/**
	 * @return the inServiceDate
	 */
	public String getInServiceDate() {
		return inServiceDate;
	}

	/**
	 * @param inServiceDate the inServiceDate to set
	 */
	public void setInServiceDate(String inServiceDate) {
		this.inServiceDate = inServiceDate;
	}

	/**
	 * @return the keyRelease
	 */
	public String getKeyRelease() {
		return keyRelease;
	}

	/**
	 * @param keyRelease the keyRelease to set
	 */
	public void setKeyRelease(String keyRelease) {
		this.keyRelease = keyRelease;
	}

	/**
	 * @return the siteUrl
	 */
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * @param siteUrl the siteUrl to set
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}


	/**
	 * @return the epmosummary
	 */
	public String getEpmosummary() {
		return epmosummary;
	}

	/**
	 * @param epmosummary the epmosummary to set
	 */
	public void setEpmosummary(String epmosummary) {
		this.epmosummary = epmosummary;
	}

	/**
	 * @return the techLeadAccount
	 */
	public String getTechLeadAccount() {
		return techLeadAccount;
	}

	/**
	 * @param techLeadAccount the techLeadAccount to set
	 */
	public void setTechLeadAccount(String techLeadAccount) {
		this.techLeadAccount = techLeadAccount;
	}

	/**
	 * @return the programMgrAccount
	 */
	public String getProgramMgrAccount() {
		return programMgrAccount;
	}

	/**
	 * @param programMgrAccount the programMgrAccount to set
	 */
	public void setProgramMgrAccount(String programMgrAccount) {
		this.programMgrAccount = programMgrAccount;
	}

	/**
	 * @return the projectMgrAccount
	 */
	public String getProjectMgrAccount() {
		return projectMgrAccount;
	}

	/**
	 * @param projectMgrAccount the projectMgrAccount to set
	 */
	public void setProjectMgrAccount(String projectMgrAccount) {
		this.projectMgrAccount = projectMgrAccount;
	}

	/**
	 * @return the prj
	 */
	public String getPrj() {
		return prj;
	}

	/**
	 * @param prj the prj to set
	 */
	public void setPrj(String prj) {
		this.prj = prj;
	}
 
	  /**
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Project [wbs=" + wbs + ", summary=" + summary
				+ ", epmosummary=" + epmosummary + ", description="
				+ description + ", technicalLead=" + technicalLead
				+ ", programManager=" + programManager + ", projectManager="
				+ projectManager + ", projectStartDate=" + projectStartDate
				+ ", projectFinishDate=" + projectFinishDate + ", pdrDate="
				+ pdrDate + ", cdrDate=" + cdrDate + ", inServiceDate="
				+ inServiceDate + ", keyRelease=" + keyRelease + ", priority="
				+ priority + ", siteUrl=" + siteUrl + ", techLeadAccount="
				+ techLeadAccount + ", programMgrAccount=" + programMgrAccount
				+ ", projectMgrAccount=" + projectMgrAccount + ", prj=" + prj
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}


}
