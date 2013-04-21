package com.seis635.project.backing;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Professor;


@ManagedBean
@RequestScoped
public class ProfBean extends AbstractBean {
	
	@EJB
	private UniversityEJB uEJB;
	
	private Professor prof = new Professor();
	private String outcome="error";
	
	public String doCreateProfessor() {
		String result = uEJB.createProfessor(prof);
		String tmpName = prof.getFirst_name();
		prof = new Professor();
		if(result.equalsIgnoreCase("success")){
			addMessage("Successfully created Professor:" + tmpName);
			outcome="success";
			return "success";
		} else {
			addMessage(result);
			outcome="error";
			return "error";
		}
	}

	//GET_SET
	public Professor getProf() {
		return prof;
	}

	public void setProf(Professor prof) {
		this.prof = prof;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
}
