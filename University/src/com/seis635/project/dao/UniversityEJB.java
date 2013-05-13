package com.seis635.project.dao;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.seis635.project.model.Course;
import com.seis635.project.model.Department;
import com.seis635.project.model.Grade;
import com.seis635.project.model.Professor;
import com.seis635.project.model.Program;
import com.seis635.project.model.RecommendedText;
import com.seis635.project.model.Registration;
import com.seis635.project.model.Sezzion;
import com.seis635.project.model.Student;
import com.seis635.project.model.Text;
import com.seis635.project.model.University;
 
/**
 * University EJB
 */
@Stateless
@LocalBean
public class UniversityEJB {

    @PersistenceContext(unitName="University")
    private EntityManager em;
    
    public String createUniversity(University university) {
       	em.persist(university);
    	return "success";
    }
    
    public String createDepartment(String u, Department d) {
    	University tmp = (University)em.createNamedQuery("University.getUniversityByName").setParameter("univname", u).getResultList().get(0);		
    	tmp.addDepartment(d);
    	d.setUniversity(tmp);
    	em.persist(d);
    	em.persist(tmp);
    	return "Success";		
    	//TODO - add error handling		
    }
    
    public List<University> listAllUniversity() {
    	return (List<University>)em.createNamedQuery("University.findAll").getResultList();
    }
    
    
    //TODO -Error handling
    public String createStudent(Student stu) {
    	
    	em.persist(stu);
    	return "success";
    	
    }
    
    //TODO -Error handling
    public String createProfessor(Professor prof) {
    	
    	em.persist(prof);
    	return "success";
    }
    
    /**
     * Default constructor. 
     */
    public UniversityEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public String dropRegistration(Registration r) {
    	Registration deleteReg = (Registration)em.createNamedQuery("Registration.getRegistration").setParameter("student_id", r.getStudent().getStudent_id())
    	.setParameter("course_id", r.getCourse().getCourse_id()).setParameter("professor_id", r.getProfessor().getProfessor_id())
    	.setParameter("sezzion_id", r.getSezzion().getSezzion_id()).getResultList().get(0);
    	
    	em.remove(deleteReg);
    	
    	return "success";
    }
    
    public List<Department> listAllDepartment() {
    	return (List<Department>)em.createNamedQuery("Department.findAll").getResultList();
    }
    
    public List<Program> listAllPrograms() {
    	return (List<Program>)em.createNamedQuery("Program.findAll").getResultList();
    }
 
    public List<Course> listAllCourses() {
    	return (List<Course>)em.createNamedQuery("Course.findAll").getResultList();
    }
    
    public List<Student> listAllStudents() {
    	return (List<Student>)em.createNamedQuery("Student.findAll").getResultList();
    }
    
    public List<String> listAllSessionSemesters() {
    	return (List<String>)em.createNamedQuery("Sezzion.getUniqueSemesters").getResultList();
    }
    
    public List<Course> listAllCoursesAndPrograms() {
    	return (List<Course>)em.createNamedQuery("Course.getCoursesAndPrograms").getResultList();
    }
    
    public List<Professor> listAllProfessors() {
    	return (List<Professor>)em.createNamedQuery("Professor.findAll").getResultList();
    }
    
    public List<Sezzion> getSessionsForCourseBySemester(Course c, String semesteryear) {
     	return (List<Sezzion>)em.createNamedQuery("Sezzion.getSessionsForCourseBySemester").setParameter("coursename", c.getName()).setParameter("semesteryear", semesteryear).getResultList();
    }
    
    public List<Registration> getRegistrationsForSemester(String semester) {
    	System.out.println("WHAT THE HELL?" + semester);
    	return (List<Registration>)em.createNamedQuery("Registration.getRegistrationsBySemester").setParameter("semesteryear", semester).getResultList();
    }
    
    public String registerForClass(Registration r) {
    	System.out.println(r.toString());
    	em.persist(r);
    	return "success";
    }
    
    
    public String createProgram(String deptName, Program p) {
    	List tmp = em.createNamedQuery("Department.getDepartmentByName").setParameter("deptname", deptName).getResultList();
    	Department dept = null;
    	
    	if(tmp.size() == 0) {
    		//TODO - add some error handling
    		return "error";
    	} else {
    		dept = (Department)tmp.get(0);
    	}
    	
    	
    	p.setDepartment(dept);
    	em.persist(p);
    	
    	tmp = dept.getPrograms();
    	tmp.add(p);
    	dept.setPrograms(tmp);
    	
    	em.persist(dept);
        
    	return "success";
    }
    
    public String createCourse(String progName, Course c) {
    	List tmp = em.createNamedQuery("Program.getProgramByName").setParameter("progname", progName).getResultList();
    	Program program = null;
    	
    	if(tmp.size() == 0) {
    		//TODO - add some error handling
    		return "error";
    	} else {
    		program = (Program)tmp.get(0);
    	}
    	
    	tmp = program.getCourses();
    	tmp.add(c);
    	
    	em.persist(c);
    	
    	em.persist(program);
        
    	return "success";
    }
    
    public String testQuery() {
    	List<Object> tmp = em.createNamedQuery("RecommendedText.getTextsBySemesterForStudent").setParameter("semesteryear", "FALL2013").setParameter("student_id", 17).setParameter("ssn", "12345678").getResultList();
    	for(Object x: tmp) {
    		if(x instanceof RecommendedText) {
    			System.out.println(((RecommendedText)x).getText().getTitle());
    		}
    		if(x instanceof Registration) {
    			System.out.println("Registration");
    		}
    		
    	}
    	return "success";
    }
    
    public String createSession(String courseName, Sezzion s, String profSSN) {
    	List tmp = em.createNamedQuery("Course.getCourseByName").setParameter("coursename", courseName).getResultList();
    	Professor p = null;
    	if(profSSN != null) {
    		p = (Professor)em.createNamedQuery("Professor.getProfessorBySSN").setParameter("ssn", profSSN).getResultList().get(0);
    	}
    	
    	Course course = null;
    	
    	if(tmp.size() == 0) {
    		//TODO - add some error handling
    		return "error";
    	} else {
    		course = (Course)tmp.get(0);
    	}
    	
    	if(p != null) {
    		if(s.getProfessors() != null) {    		
	    		
    			if(p.getSessions() != null) {
    				tmp = p.getSessions();
    				tmp.add(s);
    				p.setSessions(tmp);
    			} else {
    				tmp = new ArrayList();
    				tmp.add(s);
    				p.setSessions(tmp);
    			}
    			
    			tmp = s.getProfessors();
	    		tmp.add(p);
	    		s.setProfessors(tmp);
	    		
    		} else {
    			tmp = new ArrayList();
    			tmp.add(p);
    			s.setProfessors(tmp);
    		}
    	}
    	
    	
    	tmp = course.getSessions();
    	tmp.add(s);
    	
    	em.persist(p);
    	em.persist(s);
    	
    	em.persist(course);
        
    	return "success";
    }
    
    public String createText(Text t) {
    	em.persist(t);
    	return "success";
    }
    
    public String createRecommendedText(RecommendedText rec) {
    	em.persist(rec);
    	return "success";
    }
    
    public Student getStudentByName(String ssn) {
    	List tmp = (List<Student>)em.createNamedQuery("Student.getStudentByName").setParameter("ssn", ssn).getResultList();
    	
	    if(tmp.size() == 0) {
			//TODO - add some error handling
	    	return null;
		} else {
			return (Student)tmp.get(0); 
		}
    }
    
    public List<Course> getCoursesForProgramName(String prog) {
    	List tmp = (List<Course>)em.createNamedQuery("Course.getCourseByProgramName").setParameter("progname", prog).getResultList();
    	
	    if(tmp.size() == 0) {
			//TODO - add some error handling
	    	return null;
		} else {
			return tmp; 
		}
    }
    
    public Course getCourseByName(String name) {
    	List tmp = (List<Course>)em.createNamedQuery("Course.getCourseByName").setParameter("coursename", name).getResultList();
    	
    	if(tmp.size() == 0) {
			//TODO - add some error handling
	    	return null;
		} else {
			return (Course)tmp.get(0); 
		}
    }
    
    public List<Grade> getGradesForStudent(String student_ssn) {
    	List<Grade> tmp = em.createNamedQuery("Grade.getGradeForStudent").setParameter("id", student_ssn).getResultList();
    	
    	if(tmp.size() == 0) {
    		//TODO - add some error handling
    		addMessage("ERROR WITH GRADES");
    		return null;
    	} else {
    		return tmp;
    	}
    	
    }
    
    public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
}
