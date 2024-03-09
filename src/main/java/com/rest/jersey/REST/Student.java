package com.rest.jersey.REST;

import javax.xml.bind.annotation.XmlRootElement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@XmlRootElement
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studID;
	private String studName;
	private int avgGrade;

	public int getStudID() {
		return studID;
	}

	public void setStudID(int studID) {
		this.studID = studID;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public int getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(int avgGrade) {
		this.avgGrade = avgGrade;
	}

	@Override
	public String toString() {
		return "Student [studId=" + studID + ", studName=" + studName + ", avgGrade=" + avgGrade + "]";
	}

}
