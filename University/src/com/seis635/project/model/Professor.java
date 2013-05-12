package com.seis635.project.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@NamedQueries({
	@NamedQuery(name="Professor.getProfessorBySSN",query="SELECT p FROM Professor p WHERE p.ssn = :ssn"),
	@NamedQuery(name="Professor.findAll",query="SELECT p FROM Professor p")
})
public class Professor {

	
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long professor_id;
	
	@Column(length = 11,nullable=false)
	private String ssn;
	
	@Column(length=25)
	private String first_name;
	
	@Column(length=25)
	private String last_name;
	
	@Column(length=1)
	private String sex;
	
	@NotNull
	private double salary;
	
	@Temporal(TemporalType.DATE)
	private Date dateofbirth;
	
	@Temporal(TemporalType.DATE)
	private Date employment_date;
	
	@Null
	@Column(length=25)
	private String middle_name;

	@ManyToOne 
	@JoinColumn(name="university_id")
	private University university;
	
	@ManyToOne 
	@JoinColumn(name="department_id")
	private Department department;
	
	@ManyToMany(mappedBy="professors")
	private List<Sezzion> sessions;
	
	
	
	
	public long getProfessor_id() {
		return professor_id;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public Date getEmployment_date() {
		return employment_date;
	}

	public void setEmployment_date(Date employment_date) {
		this.employment_date = employment_date;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setProfessor_id(long professor_id) {
		this.professor_id = professor_id;
	}

	public List<Sezzion> getSessions() {
		return sessions;
	}

	public void setSessions(List<Sezzion> sessions) {
		this.sessions = sessions;
	}

	
	
}

	

