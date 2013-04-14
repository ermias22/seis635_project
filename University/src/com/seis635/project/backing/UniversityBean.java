package com.seis635.project.backing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Department;
import com.seis635.project.model.Program;
import com.seis635.project.model.Student;
import com.seis635.project.model.University;

@ManagedBean
@RequestScoped
public class UniversityBean extends AbstractBean {
	 
	@EJB
	UniversityEJB uEJB;

	protected University univ = new University();
	
	
	public List<University> doListAllUniversity() {
		return uEJB.listAllUniversity();
	}
	
	public String doCreateUniversity() {
		String result = uEJB.createUniversity(univ);

		if(result.equalsIgnoreCase("success")){
			addMessage("Successfully created University:" + univ.getName());
			return "success";
		} else {
			addMessage(result);
			return "error";
		}
	}
	
	//TODO - ADD DELETE METHOD
	
	
	//Getters and setters
	
	public University getUniv() {
		return univ;
	}

	public void setUniv(University univ) {
		this.univ = univ;
	}
}
