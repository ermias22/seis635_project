package com.seis635.project.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Course;
import com.seis635.project.model.Program;

@ViewScoped
@ManagedBean
public class TableBean extends AbstractBean implements Serializable {
	
	@EJB
	private UniversityEJB uEJB;
	
	private List<Course> courses;
	private List<Program> programs;
	
	private String programName;
	private Program selectedProgram;
	private Course selectedCourse;
	
	private List<Course> regCourses;
	
	
	
	private List<Course> filteredCourses;
	
	@PostConstruct
	public void init() {
		programs = uEJB.listAllPrograms();
		courses = uEJB.listAllCourses();
		regCourses = new ArrayList<Course>();
		filteredCourses = courses;
	}

	public List<Course> onProgramChange(AjaxBehaviorEvent event) {
		String name = (String)((UIOutput)event.getSource()).getValue();
		//DataTable dt1 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:courseContent");
		return courses = uEJB.getCoursesForProgramName(name);
		
	}
	
	public void onCourseDrop(DragDropEvent ddEvent) {
		Course course = ((Course) ddEvent.getData());
		
		regCourses.add(course);
		courses.remove(course);
		
	}
	
	public void addCourseToRegister(SelectEvent event) {

		
		Course tmp = uEJB.getCourseByName(((Course)event.getObject()).getName());
		
		
		//regCourses.add(((Course) event.getObject()));
		
		FacesMessage msg = new FacesMessage("Course selected: " + tmp.getName() + ":" + tmp.getCourse_id() + ":" + courses.contains(tmp));
		
		courses.remove(((Course) event.getObject()));
		if(!filteredCourses.isEmpty()) {
			filteredCourses.remove(((Course) event.getObject()));
		}
		
		regCourses.add(((Course) event.getObject()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	public UniversityEJB getuEJB() {
		return uEJB;
	}

	public void setuEJB(UniversityEJB uEJB) {
		this.uEJB = uEJB;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
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

	public Program getSelectedProgram() {
		return selectedProgram;
	}

	public void setSelectedProgram(Program selectedProgram) {
		this.selectedProgram = selectedProgram;
	}

	public List<Course> getFilteredCourses() {
		return filteredCourses;
	}

	public void setFilteredCourses(List<Course> filteredCourses) {
		this.filteredCourses = filteredCourses;
	}

	public List<Course> getRegCourses() {
		return regCourses;
	}

	public void setRegCourses(List<Course> regCourses) {
		this.regCourses = regCourses;
	}

	public Course getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(Course selectedCourse) {
		this.selectedCourse = selectedCourse;
	}
	
	
	
	
}
