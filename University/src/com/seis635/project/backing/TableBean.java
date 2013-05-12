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
	private List<Student> students;

	
	private List<Course> filteredCourses;
	boolean courseSelected = false;
	
	private List<String> semesters;
	private String semester;
	
	private Registration reg;
	
	private int step = 1;
	
	private List<Course> courseToRegister;
	private List<Registration> registrations;
	private Registration selectedRegistration;
	
	
	private String student_ssn;
	
	private String dayofweek;
	
	private List<Registration> registeredFor;
	
	@PostConstruct
	public void init() {
		step = 1;
		programs = uEJB.listAllPrograms();
		courses = uEJB.listAllCoursesAndPrograms();
		
		regCourses = new ArrayList<Course>();
		courseSessions = new ArrayList<Sezzion>();
		filteredCourses = courses;
		coursesToRegister = new ArrayList<Registration>();
		
		students = uEJB.listAllStudents();
		semesters = uEJB.listAllSessionSemesters();
		
		courseToRegister = new ArrayList<Course>();
		registrations = new ArrayList<Registration>();
		registeredFor= new ArrayList<Registration>();
	}

	public List<Course> onProgramChange(AjaxBehaviorEvent event) {
		String name = (String)((UIOutput)event.getSource()).getValue();
		//DataTable dt1 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:courseContent");
		return courses = uEJB.getCoursesForProgramName(name);
		
	}
	
	public void chooseSession() {
		addMessage(sessionForCourse.getName());
		System.out.println("I am here");
		System.out.println(this.getSemester());
		courseSessions = uEJB.getSessionsForCourseBySemester(sessionForCourse, this.getSemester());
		
	}
	
	
	public void removeRegistration(SelectEvent event) {
		addMessage(((Registration)event.getObject()).getSezzion().getDayofweek());
		
		registrations.remove( (Registration)event.getObject());
	}
	
	public void addRegistration(Registration r) {
		
		registrations.add(r);
	}
	
	public void addCourseToRegister(Course c) {
		addMessage(c.getName());
		courseToRegister.add(c);
	}
	
	public void doRegistration() {
		for (Registration r : registrations) {
			
			System.out.println(registrations.size());
			System.out.println(r.toString());
					
			addMessage(r.toString());
			uEJB.registerForClass(r);
		}
	}
	
	
	public void selectSession(SelectEvent event) {
		Sezzion tmpSezzion = (Sezzion)event.getObject();
			
		reg = new Registration();
		reg.setClazz(sessionForCourse);
		reg.setSezzion(tmpSezzion);
		reg.setProfessor(tmpSezzion.getProfessors().get(0));
		
		Student studentToRegister = (Student)uEJB.getStudentByName(this.getStudent_ssn());
		
		reg.setStudent(studentToRegister);
		
		for(Course c : regCourses) {
			if(c.getCourse_id() == sessionForCourse.getCourse_id()) {
				c.setUserDayOfWeek(tmpSezzion.getDayofweek());
			}
		}
		
		addRegistration(reg);
		
		//uEJB.registerForClass(reg);
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
		addCourseToRegister(course);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void populateRegisteredClasses() {
		registeredFor = uEJB.getRegistrationsForSemester(this.getSemester());
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
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Registration getReg() {
		return reg;
	}

	public void setReg(Registration reg) {
		this.reg = reg;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		addMessage(String.valueOf(step));
		this.step = step;
	}

	public List getCourseToRegister() {
		return courseToRegister;
	}

	public void setCourseToRegister(List courseToRegister) {
		this.courseToRegister = courseToRegister;
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public Registration getSelectedRegistration() {
		return selectedRegistration;
	}

	public void setSelectedRegistration(Registration selectedRegistration) {
		this.selectedRegistration = selectedRegistration;
	}

	public String getStudent_ssn() {
		return student_ssn;
	}

	public void setStudent_ssn(String student_ssn) {
		this.student_ssn = student_ssn;
	}

	public String getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}

	public List<Registration> getRegisteredFor() {
		return registeredFor;
	}

	public void setRegisteredFor(List<Registration> registeredFor) {
		this.registeredFor = registeredFor;
	}


	
	
}
