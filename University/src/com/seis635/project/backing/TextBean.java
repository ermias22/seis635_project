package com.seis635.project.backing;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.seis635.project.dao.UniversityEJB;
import com.seis635.project.model.Department;
import com.seis635.project.model.Text;
import com.seis635.project.model.University;

@ManagedBean
@RequestScoped
public class TextBean extends AbstractBean {
	
	@EJB
	private UniversityEJB uEJB;
	
	private Text text = new Text();
	
	private List<Text> textbooks;
	private List<String> semesters;
	
	private String student;
	private String semester;
	
	private int step = 1;
	
	@PostConstruct
	public void init() {
		step = 1;
		//textbooks = uEJB.getTextbookBySemesterForStudent(String student, String semester);
		semesters = uEJB.listAllSessionSemesters();
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
	

	
}
