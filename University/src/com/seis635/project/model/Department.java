package com.seis635.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Department
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Department.getDepartmentByName",query="SELECT d FROM Department d WHERE d.name = :deptname"),
	@NamedQuery(name="Department.findAll",query="SELECT d FROM Department d")
})
public class Department implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue 
	private long department_id;
	@Column(length=225)
	private String name;
	@ManyToOne
	@JoinColumn(name="university_id")
	public University university;
	
	@OneToMany
	@JoinColumn(name="program_id")
	List<Program> programs;
	
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Department() {}

	public long getDepartment_id() {
		return department_id;
	}


	public void setDepartment_id(long department_id) {
		this.department_id = department_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

}
