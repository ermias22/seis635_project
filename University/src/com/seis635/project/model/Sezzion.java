package com.seis635.project.model;


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

@Entity
@NamedQueries({
	@NamedQuery(name="Sezzion.getSessionsForCourse", query="SELECT s from Sezzion s WHERE s.course.name = :coursename"),
	@NamedQuery(name="Sezzion.getUniqueSemesters", query="SELECT distinct s.semesteryear from Sezzion s")
})
 
public class Sezzion {
	public Sezzion() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long sezzion_id;
	
	@Column(length=12 )
	private String semesteryear;
	
	@Column(length=4, nullable=true)
	private int start_time;
	@Column(length=4, nullable=true)
	private int end_time; 
	@Column(length=4, nullable=true)
	private int numofseats;
	
	@Column(length=2)
	private String dayofweek; 
	
	@Column(length=25)
	private String location;

	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@ManyToMany
	@JoinTable(name="teaches",
			joinColumns={@JoinColumn(name="sezzion_id", nullable=false)},
			inverseJoinColumns={@JoinColumn(name="professor_id", nullable=false)})
	private List<Professor> professors;
	
	public boolean equals(Object other) {
	    return (other instanceof Sezzion) && (Long.valueOf(sezzion_id) != null) 
	         ? Long.valueOf(sezzion_id).equals(Long.valueOf(((Sezzion) other).sezzion_id)) 
	         : (other == this);
	}
	

	public int hashCode() {
	    return (Long.valueOf(sezzion_id) != null) 
	         ? (getClass().hashCode() + Long.valueOf(sezzion_id).hashCode())
	         : super.hashCode();
	}
	
	
	public String calculateOpenSeats() {
		return "5";
	}
	public long getSezzion_id() {
		return sezzion_id;
		
	}

	public void setSezzion_id(long sezzion_id) {
		this.sezzion_id = sezzion_id;
	}

	public String getSemesteryear() {
		return semesteryear;
	}

	public void setSemesteryear(String semesteryear) {
		this.semesteryear = semesteryear;
	}

	public int getStart_time() {
		return start_time;
	}

	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}

	public int getEnd_time() {
		return end_time;
	}

	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}

	public int getNumofseats() {
		return numofseats;
	}

	public void setNumofseats(int numofseats) {
		this.numofseats = numofseats;
	}

	public String getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}

	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public List<Professor> getProfessors() {
		return professors;
	}


	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}
	
	
	
}
