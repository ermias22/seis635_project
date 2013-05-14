package com.seis635.project.backing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.RecommendedText;
import com.seis635.project.model.Student;
import com.seis635.project.model.Text;

@ManagedBean
@ViewScoped
public class TextBean extends AbstractBean {
	
	@EJB
	private UniversityEJB uEJB;
	
	private Text text = new Text();
	
	private List<RecommendedText> textbooks;
	private List<String> semesters;
	private List<Student> students;
	
	
	private String student;
	private String semester;
	private String student_ssn; 
	private long student_id;
	private String prof_ssn;
	
	private int step = 1;
	
	@PostConstruct
	public void init() {
		step = 1;
		//textbooks = uEJB.getTextbookBySemesterForStudent(String student, String semester);
		semesters = uEJB.listAllSessionSemesters();
		students = uEJB.listAllStudents(); 
		
	}
	
	public void populateRequiredText() {
		textbooks = uEJB.getRecommendedText(semester, student_id); 
	}
	
	public String doCreateText() {
		String result = uEJB.createText(text);
		
		if(result.equalsIgnoreCase("success")){
			addMessage("Successfully created Text:" + text.getTitle());
			return "success";
		} else {
			addMessage(result);
			return "error";
		}
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public List<RecommendedText> getTextbooks() {
		return textbooks;
	}

	public void setTextbooks(List<RecommendedText> textbooks) {
		this.textbooks = textbooks;
	}

	public List<String> getSemesters() {
		return semesters;
	}

	public void setSemesters(List<String> semesters) {
		this.semesters = semesters;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getStudent_ssn() {
		return student_ssn;
	}

	public void setStudent_ssn(String student_ssn) {
		this.student_ssn = student_ssn;
	}

	public String getProf_ssn() {
		return prof_ssn;
	}

	public void setProf_ssn(String prof_ssn) {
		this.prof_ssn = prof_ssn;
	}

	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}
	

	
}
