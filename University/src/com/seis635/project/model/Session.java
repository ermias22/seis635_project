package com.seis635.project.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Session {
	public Session() {}
	
	@Id
	@GeneratedValue
	private long session_id;
	
	@Column(length=225)
	private String semesteryear;
	
	@Temporal(TemporalType.TIME)
	private Date start_time;
	@Temporal(TemporalType.TIME)
	private Date end_time; 
	
	private int numofseats;
	
	@Column(length=2)
	private String dayofweek;

	public long getSession_id() {
		return session_id;
	}

	public void setSession_id(long session_id) {
		this.session_id = session_id;
	}

	public String getSemesteryear() {
		return semesteryear;
	}

	public void setSemesteryear(String semeseryear) {
		this.semesteryear = semesteryear;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
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
	
	
	
}
