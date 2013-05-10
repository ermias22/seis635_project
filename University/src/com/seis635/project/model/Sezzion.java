package com.seis635.project.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
public class Sezzion {
	public Sezzion() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long sezzion_id;
	
	@Column(length=12 )
	private String semesteryear;
	
	@Column(length=4, nullable=true)
	private int start_time;
	private int end_time; 
	private int numofseats;
	
	@Column(length=2)
	private String dayofweek; 

	@ManyToOne
	@JoinColumn(name="professor_id")
	private Professor professor;
	
	
	
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
	
	
	public long getSession_id() {
		return sezzion_id;
		
	}

	public void setSession_id(long session_id) {
		this.sezzion_id = session_id;
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


	public Professor getProfessor() {
		return professor;
	}


	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
	
}
