package com.seis635.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;



@Entity
public class Student {
	public Student() {}
	
	@Id
	@GeneratedValue
	private long student_id;
	
	@NotNull
	@Column(length = 11)
	private String ssn;
	
	@NotNull
	@Column(length=25)
	private String first_name;
	private String last_name;
	
	private String sex;
	
	@Temporal(TemporalType.DATE)
	private Date dateofbirth;
	
	@Null
	@Column(length=25)
	private String middle_name;

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

	
	
}
