package com.seis635.project.backing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Course;
import com.seis635.project.model.Professor;
import com.seis635.project.model.Sezzion;

@ManagedBean
@RequestScoped
public class SessionBean {

	@EJB
	UniversityEJB uEJB;
	
	private String courseName;
	private List<Course> courses;
	private List<Professor> professors;
	private Sezzion session = new Sezzion();
	private String profSSN;
	
	
	@PostConstruct
	public void init() {
		courses = uEJB.listAllCourses();
		professors = uEJB.listAllProfessors();
	}
		
	public String doCreateSession() {
		String result = uEJB.createSession(courseName,session,profSSN );

		if(result.equalsIgnoreCase("success")){
			//addMessage("Successfully created Program:" + prog.getName());
			return "success";
		} else {
			//addMessage(result);
			
			return "error";
		}
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Sezzion getSession() {
		return session;
	}

	public void setSession(Sezzion session) {
		this.session = session;
	}

	public List<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}

	public String getProfSSN() {
		return profSSN;
	}

	public void setProfSSN(String profSSN) {
		this.profSSN = profSSN;
	}
}
