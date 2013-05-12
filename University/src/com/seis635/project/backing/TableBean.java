package com.seis635.project.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Course;
import com.seis635.project.model.Program;
import com.seis635.project.model.Registration;
import com.seis635.project.model.Sezzion;
import com.seis635.project.model.Student;

@ViewScoped
@ManagedBean
public class TableBean extends AbstractBean implements Serializable {
	
	@EJB
	private UniversityEJB uEJB;
	
    // Following Bean
    @ManagedProperty(value = "#{appSessionBean}")
    private AppSessionBean appSessionBean;

	
	private List<Course> courses;
	private List<Program> programs;
	private List<Sezzion> session;
	private String programName;
	
	private Program selectedProgram;
	private Course selectedCourse;
	private Sezzion selectedSession;
	
	private Course sessionForCourse;
	private List<Sezzion> courseSessions;
	
	private List<Course> regCourses;
	
	private List<Registration> coursesToRegister;
	

	
	private List<Course> filteredCourses;
	boolean courseSelected = false;
	
	private List<String> semesters;
	private String semester;
	
	@PostConstruct
	public void init() {
		programs = uEJB.listAllPrograms();
		courses = uEJB.listAllCoursesAndPrograms();
		
		regCourses = new ArrayList<Course>();
		courseSessions = new ArrayList<Sezzion>();
		filteredCourses = courses;
		coursesToRegister = new ArrayList<Registration>();
		
		
		semesters = uEJB.listAllSessionSemesters();
	}

	public List<Course> onProgramChange(AjaxBehaviorEvent event) {
		String name = (String)((UIOutput)event.getSource()).getValue();
		//DataTable dt1 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:courseContent");
		return courses = uEJB.getCoursesForProgramName(name);
		
	}
	
	public void chooseSession() {
		addMessage(sessionForCourse.getName());
		System.out.println("I am here");
		courseSessions = uEJB.getSessionsForCourse(sessionForCourse);
		
	}
	
	public void selectSession(SelectEvent event) {
		Registration reg = new Registration();
		reg.setClazz(sessionForCourse);
		reg.setSezzion((Sezzion)event.getObject());
		
	}
	
	public void addCourseToRegister(SelectEvent event) {

		
		Course tmp = uEJB.getCourseByName(((Course)event.getObject()).getName());
		
		Course course = (Course)event.getObject();
		
		//regCourses.add(((Course) event.getObject()));
		
		FacesMessage msg = new FacesMessage("Course selected: " + tmp.getName() + ":" + tmp.getCourse_id() + ":" + courses.contains(tmp));
		
		courses.remove(course);
		if(!filteredCourses.isEmpty()) {
			filteredCourses.remove(course);
		}
		
		regCourses.add(course);
		appSessionBean.addCourseToRegister(course);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void getSessionForCourse(Course c) {
		session = uEJB.getSessionsForCourse(c);
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

	public void setAppSessionBean(AppSessionBean sessionBean) {
		this.appSessionBean = sessionBean;
	}

	public Course getSessionForCourse() {
		return sessionForCourse;
	}

	public void setSessionForCourse(Course sessionForCourse) {
		this.sessionForCourse = sessionForCourse;
	}

	public List<Sezzion> getCourseSessions() {
		return courseSessions;
	}

	public void setCourseSessions(List<Sezzion> courseSessions) {
		this.courseSessions = courseSessions;
	}

	public List<Sezzion> getSession() {
		return session;
	}

	public void setSession(List<Sezzion> session) {
		this.session = session;
	}

	public List<Registration> getCoursesToRegister() {
		return coursesToRegister;
	}

	public void setCoursesToRegister(List<Registration> coursesToRegister) {
		this.coursesToRegister = coursesToRegister;
	}


	public AppSessionBean getAppSessionBean() {
		return appSessionBean;
	}

	public boolean isCourseSelected() {
		return courseSelected;
	}

	public void setCourseSelected(boolean courseSelected) {
		this.courseSelected = courseSelected;
	}

	public Sezzion getSelectedSession() {
		return selectedSession;
	}

	public void setSelectedSessesion(Sezzion selectedSession) {
		this.selectedSession = selectedSession;
	}

	public List<String> getSemesters() {
		return semesters;
	}

	public void setSemesters(List<String> semesters) {
		this.semesters = semesters;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public void setSelectedSession(Sezzion selectedSession) {
		this.selectedSession = selectedSession;
	}
	
	
	
	
}
