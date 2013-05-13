package com.seis635.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
	@NamedQuery(name="RecommendedText.getTextsBySemesterForStudent", query="SELECT t from RecommendedText t , Registration r WHERE t.course.course_id = r.course.course_id and r.sezzion.semesteryear = :semesteryear and t.professor.ssn = :ssn and r.student.student_id = :student_id ")
})
public class RecommendedText {
	public RecommendedText () {}
	
	@Embeddable
	public static class RecommendedTextKey implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String isbn;
		private Long professor_id;
		private Long course_id;
		
		public RecommendedTextKey() {}
		public RecommendedTextKey(String isbn, long professorId, long classId) {
			this.isbn = isbn;
			this.professor_id = professorId;
			this.course_id = classId;
			
		}
		
		@Override
		public int hashCode() {
			return 1;
		}
		
		@Override
		public boolean equals(Object that) {
			return (this == that) || ((that instanceof RecommendedTextKey) && this.isbn.equals(((RecommendedTextKey) that).isbn) && this.professor_id == ((RecommendedTextKey) that).professor_id) && this.course_id == ((RecommendedTextKey)that).course_id;
		}
		public Long getProfessor_id() {
			return professor_id;
		}
		public void setProfessor_id(Long professor_id) {
			this.professor_id = professor_id;
		}
		public Long getCcourse_id() {
			return course_id;
		}
		public void setCourse_id(Long class_id) {
			this.course_id = class_id;
		}
		public String getIsbn() {
			return isbn;
		}
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		
	}

	@EmbeddedId
	private RecommendedTextKey recommended_id;
	
	@Column(nullable=false, length=10)
	private boolean required;
	
	
	@OneToOne
	@JoinColumn(name="isbn")
	@MapsId("isbn")
	private Text text;
	
	@OneToOne
	@JoinColumn(name="professor_id")
	@MapsId("professor_id")
	private Professor professor;
	
	@OneToOne
	@JoinColumn(name="course_id")
	@MapsId("course_id")
	private Course course ;

	public RecommendedTextKey getRecommended_id() {
		return recommended_id;
	}

	public void setRecommended_id(RecommendedTextKey recommended_id) {
		this.recommended_id = recommended_id;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course clazz) {
		this.course = clazz;
	}
	
}
