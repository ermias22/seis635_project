package com.seis635.project.backing;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Course;
import com.seis635.project.model.Department;
import com.seis635.project.model.Professor;
import com.seis635.project.model.Program;
import com.seis635.project.model.Student;
import com.seis635.project.model.University;

@ManagedBean(eager=true)
@ApplicationScoped
public class ApplicationBean {

	@EJB
	private UniversityEJB uEJB;
	
	@PostConstruct
	public void init() {
	}
	
	public void createDefaultData() {
		
		University u1 = new University("St. Thomas");
		University u2 = new University("U of M");
		
		uEJB.createUniversity(u1);
		uEJB.createUniversity(u2);
		
		Department d1 = new Department("Computer Science");
		Department d2 = new Department("Physics");
		
		d1.setUniversity(u1);
		d2.setUniversity(u1);
		
		uEJB.createDepartment(u1.getName(), d1);
		uEJB.createDepartment(u1.getName(), d2);
		
		
		Program p1 = new Program();
		p1.setName("GPS");
		p1.setDescription("Software Engineering Program");
		p1.setRequired_credits(100);
		 
		Program p2 = new Program();
		p2.setName("GPM");
		p2.setDescription("Software Engineering Management");
		p2.setRequired_credits(100);
		
		uEJB.createProgram(d1.getName(), p1);
		uEJB.createProgram(d1.getName(), p2);
		
		Course c1 = new Course();
		c1.setName("SEIS635");
		c1.setDescription("Software Analysis");
		c1.setCredit(3);
		
		Course c2 = new Course();
		c2.setName("SEIS630");
		c2.setDescription("Database Management");
		c2.setCredit(3);
		
		uEJB.createCourse(p1.getName(), c1);
		uEJB.createCourse(p1.getName(), c2);
		
		//create students
		
		Student s1 = new Student();
		s1.setFirst_name("Matt");
		s1.setLast_name("Kesinger");
		s1.setSex("M");
		s1.setSsn("12345");
		s1.setDateofbirth(new Date("03/25/1982"));
		s1.setProgram(p1);
		
		uEJB.createStudent(s1);
		
		Student s2 = new Student();
		s2.setFirst_name("John");
		s2.setLast_name("Doe");
		s2.setSex("M");
		s2.setSsn("12345");
		s2.setDateofbirth(new Date("01/01/1980"));
		s2.setProgram(p1);
		
		uEJB.createStudent(s2);
		
		//create professors
		
		Professor pr1 = new Professor();
		pr1.setFirst_name("Brad");
		pr1.setLast_name("Rubin");
		pr1.setSalary(250000);
		pr1.setDateOfBirth(new Date("02/02/1964"));
		pr1.setSex("M");
		pr1.setEmployment_date(new Date("05/05/2005"));
		pr1.setDepartment(d1);
		pr1.setSsn("123456");
		pr1.setUniversity(u1);
		
		Professor pr2 = new Professor();
		pr2.setFirst_name("Saeed");
		pr2.setLast_name("Rahimi");
		pr2.setSalary(100000);
		pr2.setDateOfBirth(new Date("02/02/1960"));
		pr2.setSex("M");
		pr2.setEmployment_date(new Date("05/05/2004"));
		pr2.setDepartment(d1);
		pr2.setSsn("1234567");
		pr2.setUniversity(u1);
		
		uEJB.createProfessor(pr1);
		uEJB.createProfessor(pr2);
		
	}
	
}
