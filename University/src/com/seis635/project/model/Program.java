package com.seis635.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
public class Program {

	@Id
	@GeneratedValue
	private long program_id;
	
	@NotNull
	@Column(length=225)
	private String name;
	
	@Null
	@Column(length=225)
	private String desc;
	
	private int required_credits;
	
	
	@ManyToOne
	@JoinColumn(name="department_id")
	public Department department;

	public Program() {}
	
	public long getProgram_id() {
		return program_id;
	}

	public void setProgram_id(long program_id) {
		this.program_id = program_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getRequired_credits() {
		return required_credits;
	}

	public void setRequired_credits(int required_credits) {
		this.required_credits = required_credits;
	}
	
	
}
