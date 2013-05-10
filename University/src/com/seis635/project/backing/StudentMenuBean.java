package com.seis635.project.backing;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class StudentMenuBean {

	public StudentMenuBean() {}
	
	private String page = "/Student/welcome.xhtml";
	
	    public void save() {  
	        addMessage("Data saved");  
	    }  
	      
	    public void update() {  
	        addMessage("Data updated");  
	    }  
	      
	    public void delete() {  
	        addMessage("Data deleted");  
	    }  
	      
	    public void addMessage(String summary) {  
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	        
	    }

		public String getPage() {
			return page;
		}

		public void setPage(String page) {
			this.page = page;
		}  
		
}