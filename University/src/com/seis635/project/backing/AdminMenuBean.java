package com.seis635.project.backing;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class AdminMenuBean {

	public AdminMenuBean() {}
	
	private String page = "/Admin/welcome.xhtml";
	
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

	    public void studentnavigate(String outcome) {
	    	if(outcome.equalsIgnoreCase("success")) {
	    		setPage("/Admin/student.xhtml");
	    	} else {
	    		
	    	}
	    			
	    }
		public String getPage() {
			return page;
		}

		public void setPage(String page) {
			this.page = page;
		}  
		
}
