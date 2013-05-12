package com.seis635.project.backing;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Course;
import com.seis635.project.model.Registration;

@ManagedBean
@SessionScoped
public class AppSessionBean {
	
	@EJB
	private UniversityEJB uEJB;
	
	@PostConstruct
	public void init() {
	
	}


	
	
}
