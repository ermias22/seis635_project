package com.seis635.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Class {
	
	public Class() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long class_id;
	
	@Column(length=225)
	private String name;
	private String desc;
	private int credit;  
	
	public long getClass_id() {
		return class_id;
	}
	public void setClass_id(long class_id) {
		this.class_id = class_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
}
