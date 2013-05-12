package com.seis635.project.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name="Course.getCourseByProgramName",query="SELECT c FROM Course c WHERE c.program.name = :progname"),
	@NamedQuery(name="Course.findAll",query="SELECT c FROM Course c"),
	@NamedQuery(name="Course.getCourseByName",query="SELECT c FROM Course c WHERE c.name = :coursename"),
	@NamedQuery(name="Course.getCoursesAndPrograms", query="SELECT c from Course c JOIN c.program p")
})
public class Course {
	
	public Course() {}
	
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long course_id;
	
	@Column(length=225)
	private String name;
	private String description;
	private int credit;  
	
	@ManyToOne
	@JoinColumn(name="program_id")
	private Program program; 
	
	@OneToMany
	@JoinColumn(name="course_id")
	private List<Sezzion> sessions;
	
	@Transient
	private String userDayOfWeek;
	
	
	public boolean equals(Object other) {
	    return (other instanceof Course) && (Long.valueOf(course_id) != null) 
	         ? Long.valueOf(course_id).equals(Long.valueOf(((Course) other).course_id)) 
	         : (other == this);
	}
	

	public int hashCode() {
	    return (Long.valueOf(course_id) != null) 
	         ? (getClass().hashCode() + Long.valueOf(course_id).hashCode())
	         : super.hashCode();
	}
	
	public long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return description;
	}
	public void setDesc(String desc) {
		this.description = desc;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}


	public List<Sezzion> getSessions() {
		return sessions;
	}


	public void setSessions(List<Sezzion> sessions) {
		this.sessions = sessions;
	}


	public String getUserDayOfWeek() {
		return userDayOfWeek;
	}


	public void setUserDayOfWeek(String userDayOfWeek) {
		this.userDayOfWeek = userDayOfWeek;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.course_id) + "|" + this.program.getName() + "|" + this.name + "|" + this.description;	
	}
}
