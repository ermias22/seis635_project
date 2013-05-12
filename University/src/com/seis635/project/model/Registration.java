package com.seis635.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name="Grade.getGradesForStudent",query="SELECT g FROM Grade g WHERE g.grade_id.student_id = :id"),
	@NamedQuery(name="Registration.getRegistrationsBySemester",query="SELECT r from Registration r where r.sezzion.semesteryear = :semesteryear")
})
public class Registration {
	
		@Embeddable
		public static class RegistrationKey implements Serializable {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			private Long student_id;
			private Long sezzion_id;
			private Long professor_id;
			private Long class_id;
			
			public RegistrationKey() {}
			public RegistrationKey(long studentId, long sessionId, long professorId, long classId) {
				this.student_id = studentId;
				this.sezzion_id = sessionId;
				this.professor_id = professorId;
				this.class_id = classId;
				
			}
			
			@Override
			public int hashCode() {
				return 1;
			}
			
			@Override
			public boolean equals(Object that) {
				return (this == that) || ((that instanceof RegistrationKey) && this.student_id.equals(((RegistrationKey) that).student_id) && this.sezzion_id.equals(((RegistrationKey) that).sezzion_id));
			}
			public Long getStudent_id() {
				return student_id;
			}
			public void setStudent_id(Long student_id) {
				this.student_id = student_id;
			}
			public Long getSezzion_id() {
				return sezzion_id;
			}
			public void setSezzion_id(Long sezzion_id) {
				this.sezzion_id = sezzion_id;
			}
			public Long getProfessor_id() {
				return professor_id;
			}
			public void setProfessor_id(Long professor_id) {
				this.professor_id = professor_id;
			}
			public Long getClass_id() {
				return class_id;
			}
			public void setClass_id(Long class_id) {
				this.class_id = class_id;
			}
			
		}

		@EmbeddedId
		private RegistrationKey registration_id;
		
		public Registration() {}
				
		@OneToOne
		@JoinColumn(name="sezzion_id")
		@MapsId("sezzion_id")
		private Sezzion sezzion;
		
		@OneToOne
		@JoinColumn(name="student_id")
		@MapsId("student_id")
		private Student student;
		
		@OneToOne
		@JoinColumn(name="professor_id")
		@MapsId("professor_id")
		private Professor professor;
		
		@OneToOne
		@JoinColumn(name="class_id")
		@MapsId("class_id")
		private Course clazz ;
		
		@Temporal(TemporalType.DATE)
		private Date enrollment_date;

		public RegistrationKey getRegistration_id() {
			return registration_id;
		}

		public void setRegistration_id(RegistrationKey registration_id) {
			this.registration_id = registration_id;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		public Professor getProfessor() {
			return professor;
		}

		public void setProfessor(Professor professor) {
			this.professor = professor;
		}

		public Course getClazz() {
			return clazz;
		}

		public void setClazz(Course clazz) {
			this.clazz = clazz;
		}

		public Date getEnrollment_date() {
			return enrollment_date;
		}

		public void setEnrollment_date(Date enrollment_date) {
			this.enrollment_date = enrollment_date;
		}

		public Sezzion getSezzion() {
			return sezzion;
		}

		public void setSezzion(Sezzion sezzion) {
			this.sezzion = sezzion;
		}

		@Override
		public String toString() {
			return String.valueOf(this.clazz.getCourse_id()) + "|" + this.clazz.getName() + "|" + this.clazz.getProgram().getName() + "|" + this.sezzion.getDayofweek() + "|" + this.professor.getLast_name();
		}
}
