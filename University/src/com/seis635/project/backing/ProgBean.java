package com.seis635.project.backing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Department;
import com.seis635.project.model.Program;

@ManagedBean
@RequestScoped
public class ProgBean extends AbstractBean { 
	@EJB
	UniversityEJB uEJB;
	
	private String deptName;
	private List<Department> departments;
	private Program prog = new Program();
	
	@PostConstruct
	public void init() {
		departments = uEJB.listAllDepartment(); 
	}
	
	public List<Department> doListAllDepartments() {
		return uEJB.listAllDepartment();
	}
	
	public String doCreateProgram() {
		String result = uEJB.createProgram(deptName,prog);

		if(result.equalsIgnoreCase("success")){
			//addMessage("Successfully created Program:" + prog.getName());
			return "success";
		} else {
			//addMessage(result);
			
			return "error";
		}
	}

	
	//TODO - ADD DELETE METHOD
	
	//GET-SET
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Program getProg() {
		return prog;
	}

	public void setProg(Program prog) {
		this.prog = prog;
	}
	
	//TODO - ADD DELETE
}
