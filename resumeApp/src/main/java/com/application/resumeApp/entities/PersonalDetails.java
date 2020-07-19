package com.application.resumeApp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = "getResumeByName", query = "from PersonalDetails personal join personal.workExperience work where work.workExperience >= :workExp"),
		@NamedQuery(name = "getResumeByExperience", query = "from PersonalDetails personal where personal.name = :name") })
public class PersonalDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int pId;
	private String name;
	private String emailId;
	private String address;
	private String primaryContactNumber;
	private String secondaryContactNumber;
	@OneToMany(cascade = CascadeType.ALL)
	private List<EducationDetails> educationdetails;
	@OneToMany(cascade = CascadeType.ALL)
	private List<WorkExperience> workExperience;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EducationDetails> getEducationdetails() {
		return educationdetails;
	}

	public void setEducationdetails(List<EducationDetails> educationdetails) {
		this.educationdetails = educationdetails;
	}

	public List<WorkExperience> getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(List<WorkExperience> workExperience) {
		this.workExperience = workExperience;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getPrimaryContactNumber() {
		return primaryContactNumber;
	}

	public void setPrimaryContactNumber(String primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
	}

	public String getSecondaryContactNumber() {
		return secondaryContactNumber;
	}

	public void setSecondaryContactNumber(String secondaryContactNumber) {
		this.secondaryContactNumber = secondaryContactNumber;
	}

}
