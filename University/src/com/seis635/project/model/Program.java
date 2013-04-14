package com.seis635.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Program {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long program_id;
	
	@NotNull
	@Column(length=25) 
	private String name;
	
	@Column(length=225, nullable=true)
	private String description;
	@Column(nullable=true)
	private int required_credits;
	
	@OneToMany
	@JoinColumn(name="class_id")
	public List<Class> classes;
	
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
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public int getRequired_credits() {
		return required_credits;
	}

	public void setRequired_credits(int required_credits) {
		this.required_credits = required_credits;
	}
	
	
}
