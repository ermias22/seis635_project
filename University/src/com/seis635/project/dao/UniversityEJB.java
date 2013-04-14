package com.seis635.project.dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.seis635.project.model.Department;
import com.seis635.project.model.Grade;
import com.seis635.project.model.Program;
import com.seis635.project.model.Student;
import com.seis635.project.model.University;
 
/**
 * Session Bean implementation class UniversityEJB
 */
@Stateless
@LocalBean
public class UniversityEJB {

    /**
     * Default constructor. 
     */
    public UniversityEJB() {
        // TODO Auto-generated constructor stub
    }
 
    @PersistenceContext(unitName="University")
    private EntityManager em;
    
    public String createUniversity(University university) {
    	
    	em.persist(university);
    	return "success";
    	
    }
    
    public String createStudent(Student stu) {
    	
    	em.persist(stu);
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
    
    public List<Department> listAllDepartment() {
    	return (List<Department>)em.createNamedQuery("Department.findAll").getResultList();
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
    
    public List<Department> getAllDepartments() {
    	List<Department> depts = (List<Department>) em.createNamedQuery("Department.findAll").getResultList();
    	return depts;
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
