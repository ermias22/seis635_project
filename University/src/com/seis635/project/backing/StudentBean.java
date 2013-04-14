package com.seis635.project.backing;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Student;

@ManagedBean
@ViewScoped
public class StudentBean extends AbstractBean {
	
	@EJB
	private UniversityEJB uEJB;
	
	private Student stu = new Student();
	private String outcome="error";
	
	public String doCreateStudent() {
		String result = uEJB.createStudent(stu);

		if(result.equalsIgnoreCase("success")){
			addMessage("Successfully created University:" + stu.getFirst_name());
			outcome="success";
			return "success";
		} else {
			addMessage(result);
			outcome="error";
			return "error";
		}
	}

	//GET_SET
	public Student getStu() {
		return stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
}
