package com.seis635.project.backing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Registration;

@ManagedBean
@ViewScoped
public class ScheduleBean {
	
	@EJB
	private UniversityEJB uEJB;

	private List<Registration> registeredFor;
	private String semester;
	private List<String> semesters;
	
	private int step = 1;
	
	@PostConstruct
	public void init() {
		step = 1;
		
		semesters = uEJB.listAllSessionSemesters();
	}

	public List<Registration> getRegisteredFor() {
		return registeredFor;
	}

	public void setRegisteredFor(List<Registration> registeredFor) {
		this.registeredFor = registeredFor;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public List<String> getSemesters() {
		return semesters;
	}

	public void setSemesters(List<String> semesters) {
		this.semesters = semesters;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	
}
