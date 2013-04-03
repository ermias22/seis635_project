package com.seis635.project.backing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Department;
import com.seis635.project.model.Program;
import com.seis635.project.model.University;

@ManagedBean
@RequestScoped
public class UniversityController {
	 
	@EJB
	UniversityEJB uEJB;

	protected University univ = new University();
	protected Department dept = new Department();
	
	protected String univName;
	
	
	protected List<University> universities;
	protected Program prog = new Program();
	
	@PostConstruct
	public void init() {
		universities = uEJB.listAllUniversity();
	}
	//Call EJB Operations

	public String doCreateUniversity() {
		uEJB.createUniversity(univ);
		return "Success";
	}
	
	public String doCreateDepartment() {
		return uEJB.createDepartment(univName,dept);
		
	}
	
	public List<University> doListAllUniversity() {
		return uEJB.listAllUniversity();
	}
	
	public String doCreateProgram() {
		return uEJB.createProgram(prog,dept);
	}

	
	//Getters and setters
	
	public Program getProg() {
		return prog;
	}

	public void setProg(Program prog) {
		this.prog = prog;
	}
	
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public University getUniv() {
		return univ;
	}

	public void setUniv(University univ) {
		this.univ = univ;
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
