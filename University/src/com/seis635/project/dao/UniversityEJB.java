package com.seis635.project.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.seis635.project.model.Department;
import com.seis635.project.model.Program;
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
    
    public String createDepartment(String u, Department d) {
    	University tmp = (University)em.createNamedQuery("University.getUniversityByName").setParameter("univname", u).getResultList().get(0);		
    	tmp.addDepartment(d);
    	d.setUniversity(tmp);
    	em.persist(d);
    	em.persist(tmp);
    	
    	return "Success";		
    			
    }
    
    public List<University> listAllUniversity() {
    	return (List<University>)em.createNamedQuery("University.findAll").getResultList();
    }
    
    public String createProgram(Program p, Department d) {
    	List tmp = em.createNamedQuery("Department.getDepartmentByName").setParameter("deptname", d.getName()).getResultList();
    	Department dept = null;
    	
    	if(tmp.size() == 0) {
    		//TODO - add some error handling
    		return "error";
    	} else {
    		dept = (Department)tmp.get(0);
    	}
    	
    	tmp = dept.getPrograms();
    	
    	em.persist(p);
    	
    	tmp.add(p);
    	
    	em.persist(tmp);
        
    	return "success";
    }
    
    public List<Department> getAllDepartments() {
    	List<Department> depts = (List<Department>) em.createNamedQuery("Department.findAll").getResultList();
    	return depts;
    }
    
    
}
