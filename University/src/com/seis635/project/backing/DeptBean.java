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
import com.seis635.project.model.University;

@ManagedBean
@RequestScoped
public class DeptBean extends AbstractBean {
	
	@EJB
	private UniversityEJB uEJB;
	
	private Department dept = new Department();
	
	private List<University> universities;
	
	private String univName;
	
	@PostConstruct
	public void init() {
		universities = uEJB.listAllUniversity();
	}
	
	public String doCreateDepartment() {
		String result = uEJB.createDepartment(univName,dept);
		
		if(result.equalsIgnoreCase("success")){
			addMessage("Successfully created Department:" + dept.getName());
			return "success";
		} else {
			addMessage(result);
			return "error";
		}
	}
	
	//TODO - ADD DELETE
	
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public List<University> getUniversities() {
		return universities;
	}

	public void setUniversities(List<University> universities) {
		this.universities = universities;
	}

	public String getUnivName() {
		return univName;
	}

	public void setUnivName(String univName) {
		this.univName = univName;
	}

	
}
