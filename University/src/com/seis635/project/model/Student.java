package com.seis635.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@NamedQueries({
	@NamedQuery(name="Student.getStudentByName",query="SELECT s FROM Student s WHERE s.ssn = :ssn"),
	@NamedQuery(name="Student.findAll",query="SELECT s FROM Student s")
})
public class Student {
	 
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long student_id;
	
	@Column(length = 11)
	private String ssn;
	
	@Column(length=25)
	private String first_name;
	private String last_name;
	@Column(length=1)
	private String sex; 
	
	@Temporal(TemporalType.DATE)
	private Date dateofbirth;
	
	@Column(length=25,nullable=true)
	private String middle_name;

	@ManyToOne
	@JoinColumn(name="program_id")
	private Program program;
	
	
	public Student() {}
	
	public Student(String firstName, String lastName, String ssn, String sex, Date dob) {
		this.first_name = firstName;
		this.last_name = lastName;
		this.ssn = ssn;
		this.sex = sex;
		this.dateofbirth = dob;
	}
	
	
	//GET-SET
	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateofbirth;
	}

	public void setDateOfBirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	
	
}
