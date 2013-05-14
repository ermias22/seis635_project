package com.seis635.project.backing;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Course;
import com.seis635.project.model.Department;
import com.seis635.project.model.Professor;
import com.seis635.project.model.Program;
import com.seis635.project.model.RecommendedText;
import com.seis635.project.model.Registration;
import com.seis635.project.model.Sezzion;
import com.seis635.project.model.Student;
import com.seis635.project.model.Text;
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
		
		Department d1 = new Department("SEIS");
		Department d2 = new Department("PHYS");
		Department d3 = new Department("ETLS");
		
		d1.setDescription("Computer Science Department");
		d1.setUniversity(u1);
		
		d2.setDescription("Physics Department");
		d2.setUniversity(u1);
		
		d3.setDescription("Engineering Department");
		d3.setUniversity(u1);
		
		String univName = u1.getName();
		
		uEJB.createDepartment(univName, d1);
		uEJB.createDepartment(univName, d2);
		uEJB.createDepartment(univName, d3);
		
		
		Program p1 = new Program();
		p1.setName("GPS");
		p1.setDescription("Software Engineering Program");
		p1.setRequired_credits(100);
		 
		Program p2 = new Program();
		p2.setName("GPM");
		p2.setDescription("Software Engineering Management");
		p2.setRequired_credits(100);
		
		Program p3 = new Program();
		p3.setName("GEE");
		p3.setDescription("Electrical Engineering");
		p3.setRequired_credits(100);
		
		uEJB.createProgram(d1.getName(), p1);
		uEJB.createProgram(d1.getName(), p2);
		uEJB.createProgram(d1.getName(), p3);
		
		Course c1 = new Course();
		c1.setName("635");
		c1.setDescription("Software Analysis");
		c1.setCredit(3);
		c1.setProgram(p1);
		
		Course c2 = new Course();
		c2.setName("630");
		c2.setDescription("Database Management");
		c2.setCredit(3);
		c2.setProgram(p1);
		
		Course c3 = new Course();
		c3.setName("601");
		c3.setDescription("Foundations of Software Development");
		c3.setCredit(3);
		c3.setProgram(p1);
		
		Course c4 = new Course();
		c4.setName("640");
		c4.setDescription("Operating Systems Design");
		c4.setCredit(3);
		c4.setProgram(p1);
		
		Course c5 = new Course();
		c5.setName("645");
		c5.setDescription("Computer and Network Communication");
		c5.setCredit(3);
		c5.setProgram(p1);
		
		Course c6 = new Course();
		c6.setName("720");
		c6.setDescription("Analog Communication Systems");
		c6.setCredit(3);
		c6.setProgram(p3);
		
		Course c7 = new Course();
		c7.setName("721");
		c7.setDescription("Digital Communication Systems");
		c7.setCredit(3);
		c7.setProgram(p3);
		
		Course c8 = new Course();
		c8.setName("600");
		c8.setDescription("Intro to Software Engineering");
		c8.setCredit(3);
		c8.setProgram(p1);
		
		uEJB.createCourse(p1.getName(), c1);
		uEJB.createCourse(p1.getName(), c2);
		uEJB.createCourse(p1.getName(), c3);
		uEJB.createCourse(p1.getName(), c4);
		uEJB.createCourse(p1.getName(), c5);
		uEJB.createCourse(p1.getName(), c8);
		
		uEJB.createCourse(p3.getName(), c6);
		uEJB.createCourse(p3.getName(), c7);
		
		
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
		s2.setSsn("12340");
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
		
		Professor pr3 = new Professor();
		pr3.setFirst_name("Chi");
		pr3.setLast_name("Lai");
		pr3.setSalary(100000);
		pr3.setDateOfBirth(new Date("03/03/1965"));
		pr3.setSex("M");
		pr3.setEmployment_date(new Date("03/03/2003"));
		pr3.setDepartment(d1);
		pr3.setSsn("12345678");
		pr3.setUniversity(u1);
		
		uEJB.createProfessor(pr1);
		uEJB.createProfessor(pr2);
		uEJB.createProfessor(pr3);
		
		Sezzion ses1 = new Sezzion();
		ses1.setDayofweek("SA");
		ses1.setStart_time(830);
		ses1.setEnd_time(1530);
		ses1.setNumofseats(30);
		ses1.setSemesteryear("FALL2013");
		ses1.setLocation("OSS 325");
		
		Sezzion ses2 = new Sezzion();
		ses2.setDayofweek("TH");
		ses2.setStart_time(1730);
		ses2.setEnd_time(2030);
		ses2.setNumofseats(30);
		ses2.setSemesteryear("FALL2013");
		ses2.setLocation("OSS 326");
		
		Sezzion ses3 = new Sezzion();
		ses3.setDayofweek("SA");
		ses3.setStart_time(830);
		ses3.setEnd_time(1530);
		ses3.setNumofseats(30);
		ses3.setSemesteryear("FALL2013");
		ses3.setLocation("OSS 325");
		
		
		uEJB.createSession(c8.getName(), ses1, pr3.getSsn());
		uEJB.createSession(c8.getName(), ses2, pr2.getSsn());
		uEJB.createSession(c1.getName(), ses3, pr1.getSsn());
		
		
		Text t1 = new Text();
		t1.setIsbn("9781848009066");
		t1.setDescription("Software Engineering");
		t1.setTitle("Human-Centered Software Engineering");
		
		Text t2 = new Text();
		t2.setIsbn("1111969604");
		t2.setDescription("Database Systems");
		t2.setTitle("Database Systems Design Implementation Management");
		
		Text t3 = new Text();
		t3.setIsbn("9780073294414");
		t3.setDescription("Sotware Analysis");
		t3.setTitle("Simulation Modeling and Analysis with Expertfit Software: 4th Edition");
		
		uEJB.createText(t1);
		uEJB.createText(t2);
		uEJB.createText(t3);
		
		RecommendedText rec1 = new RecommendedText() ;
		rec1.setRequired(true);
		rec1.setCourse(c8);
		rec1.setProfessor(pr3);
		rec1.setText(t1);
		
		RecommendedText rec2 = new RecommendedText();
		rec2.setRequired(true);
		rec2.setCourse(c8);
		rec2.setProfessor(pr3);
		rec2.setText(t3);
		
		uEJB.createRecommendedText(rec1);
		uEJB.createRecommendedText(rec2);
		
		Registration reg = new Registration();
		reg.setCourse(c8);
		reg.setProfessor(pr3);
		reg.setSezzion(ses3);
		reg.setStudent(s1);
		
		uEJB.registerForClass(reg);
		
		
		
		addMessage("Default Data Added Successfully");
	}
	
    public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
	
	
}
