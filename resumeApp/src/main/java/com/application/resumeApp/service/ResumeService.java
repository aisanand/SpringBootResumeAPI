package com.application.resumeApp.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.application.resumeApp.dto.PersonalDetailsDto;
import com.application.resumeApp.entities.PersonalDetails;

public interface ResumeService {

	public PersonalDetails addResume(PersonalDetailsDto personalDetails);

	public List<PersonalDetails> getAllCandidates();

	public List<PersonalDetailsDto> getAllCandidatesBasedOnExperience(int workExp);

	public List<PersonalDetailsDto> getAllCandidatesBasedOnName(String name);

	public HttpStatus deleteAllCandidatesBasedOnId(int id);

}
