package com.application.resumeApp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class PersonalDetailsDto {

	private int pId;
	private String name;
	private String emailId;
	private String address;
	private String primaryContactNumber;
	private String secondaryContactNumber;
	private List<EducationDetailsDto> educationdetails;
	private List<WorkExperienceDto> workExperience;

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

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

	public List<EducationDetailsDto> getEducationdetails() {
		return educationdetails;
	}

	public void setEducationdetails(List<EducationDetailsDto> educationdetails) {
		this.educationdetails = educationdetails;
	}

	public List<WorkExperienceDto> getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(List<WorkExperienceDto> workExperience) {
		this.workExperience = workExperience;
	}

}
