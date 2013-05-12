package com.seis635.project.backing;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Course;
import com.seis635.project.model.Student;

@ManagedBean
@SessionScoped
public class AppSessionBean {
	
	@EJB
	private UniversityEJB uEJB;
	
	private List courseToRegister;
	
	private String student_ssn;
	private List<Student> students;
	
	
	private String semester;

	
	@PostConstruct
	public void init() {
		courseToRegister = new ArrayList();
		students = uEJB.listAllStudents();
		
	}
	
	public void addCourseToRegister(Course c) {
		courseToRegister.add(c);
	}

	public List getCourseToRegister() {
		return courseToRegister;
	}

	public void setCourseToRegister(List courseToRegister) {
		this.courseToRegister = courseToRegister;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}



	public UniversityEJB getuEJB() {
		return uEJB;
	}

	public void setuEJB(UniversityEJB uEJB) {
		this.uEJB = uEJB;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getStudent_ssn() {
		return student_ssn;
	}

	public void setStudent_ssn(String student_ssn) {
		this.student_ssn = student_ssn;
	}
}
