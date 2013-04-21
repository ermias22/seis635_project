package com.seis635.project.backing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Course;
import com.seis635.project.model.Department;
import com.seis635.project.model.Program;

@ManagedBean
@RequestScoped
public class CourseBean extends AbstractBean {
	
	@EJB
	private UniversityEJB uEJB;
	
	private Course course = new Course();
	private List<Program> programs;
	
	private String programName;
	
	@PostConstruct
	public void init() {
		programs = uEJB.listAllPrograms();  
	}
	
	public String doCreateCourse() {
		String result = uEJB.createCourse(programName,course);

		if(result.equalsIgnoreCase("success")){
			//addMessage("Successfully created Program:" + prog.getName());
			return "success";
		} else {
			//addMessage(result);
			
			return "error";
		}
	}

	public UniversityEJB getuEJB() {
		return uEJB;
	}

	public void setuEJB(UniversityEJB uEJB) {
		this.uEJB = uEJB;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
}
