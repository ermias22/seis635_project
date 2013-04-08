package com.seis635.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Grade {
	
	@Embeddable
	public static class GradeKey implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private Long studentId;
		private Long sessionId;
		
		public GradeKey() {}
		public GradeKey(long studentId, long sessionId) {
			this.studentId = studentId;
			this.sessionId = sessionId;
		}
		
		@Override
		public int hashCode() {
			return 1;
		}
		
		@Override
		public boolean equals(Object that) {
			return (this == that) || ((that instanceof GradeKey) && this.studentId.equals(((GradeKey) that).studentId) && this.sessionId.equals(((GradeKey) that).sessionId));
		}
		
	}

	@EmbeddedId
	private GradeKey grade_id;
	
	public Grade() {}
	
	
	@OneToOne
	@JoinColumn(name="session_id")
	@MapsId("sessionId")
	Session session;
	
	@OneToOne
	@JoinColumn(name="student_id")
	@MapsId("studentId")
	Student student;
	
	private long grade;
	@Column(length=2)
	private String status;
	
	@Temporal(TemporalType.DATE)
	private Date date_received;

	public GradeKey getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(GradeKey grade_id) {
		this.grade_id = grade_id;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public long getGrade() {
		return grade;
	}

	public void setGrade(long grade) {
		this.grade = grade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate_received() {
		return date_received;
	}

	public void setDate_received(Date date_received) {
		this.date_received = date_received;
	}
	
	
	
}
