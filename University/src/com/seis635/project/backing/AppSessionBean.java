package com.seis635.project.backing;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.seis635.project.model.Course;

@ManagedBean
@SessionScoped
public class AppSessionBean {
	
	private List courseToRegister;
	
	@PostConstruct
	public void init() {
		courseToRegister = new ArrayList(); 
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
}
