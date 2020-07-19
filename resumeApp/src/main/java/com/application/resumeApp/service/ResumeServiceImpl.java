package com.application.resumeApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.application.resumeApp.dto.EducationDetailsDto;
import com.application.resumeApp.dto.PersonalDetailsDto;
import com.application.resumeApp.dto.WorkExperienceDto;
import com.application.resumeApp.entities.EducationDetails;
import com.application.resumeApp.entities.PersonalDetails;
import com.application.resumeApp.entities.WorkExperience;
import com.application.resumeApp.exception.ResumeAppException;
import com.application.resumeApp.repository.PersonalDetailsRepository;

@Service
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	PersonalDetailsRepository personalDetailsRepository;

	@Override
	@Transactional
	public PersonalDetails addResume(PersonalDetailsDto personalDetailsDto) {
		PersonalDetails personaldetail = new PersonalDetails();
		String phoneNum = personalDetailsDto.getPrimaryContactNumber();
		Optional<PersonalDetails> personal = personalDetailsRepository.findByPrimaryContactNumber(phoneNum);
		if (!personal.isPresent()) {
			List<EducationDetailsDto> educations = personalDetailsDto.getEducationdetails();
			List<EducationDetails> education = new ArrayList<>();
			for (EducationDetailsDto educationdetails : educations) {
				EducationDetails edu = new EducationDetails();
				edu.setDegree(educationdetails.getDegree());
				edu.setAreaOfStudy(educationdetails.getAreaOfStudy());
				edu.setCollegeName(educationdetails.getCollegeName());
				edu.seteId(educationdetails.geteId());
				edu.setEndDate(educationdetails.getEndDate());
				edu.setStartDate(educationdetails.getStartDate());
				education.add(edu);
			}
			List<WorkExperienceDto> workExperienceDtos = personalDetailsDto.getWorkExperience();
			List<WorkExperience> workExperiences = new ArrayList<>();
			for (WorkExperienceDto work : workExperienceDtos) {
				WorkExperience workexp = new WorkExperience();
				workexp.setAchievements(work.getAchievements());
				workexp.setDesignation(work.getDesignation());
				workexp.setEndDate(work.getEndDate());
				workexp.setJobDescription(work.getDesignation());
				workexp.setSkills(work.getSkills());
				workexp.setStartDate(work.getStartDate());
				workexp.setwId(work.getwId());
				workexp.setWorkExperience(work.getWorkExperience());
				workExperiences.add(workexp);
			}
			personaldetail.setName(personalDetailsDto.getName());
			personaldetail.setAddress(personalDetailsDto.getAddress());
			personaldetail.setEducationdetails(education);
			personaldetail.setWorkExperience(workExperiences);
			personaldetail.setEmailId(personalDetailsDto.getEmailId());
			personaldetail.setpId(personalDetailsDto.getpId());
			personaldetail.setPrimaryContactNumber(personalDetailsDto.getPrimaryContactNumber());
			personaldetail.setSecondaryContactNumber(personalDetailsDto.getSecondaryContactNumber());
			return personalDetailsRepository.save(personaldetail);
		} else {
			throw new ResumeAppException("Profile ALready found for contact Number " + phoneNum);
		}
	}

	@Override
	public List<PersonalDetails> getAllCandidates() {
		List<PersonalDetails> personalDetails = personalDetailsRepository.findAll();
		if (!personalDetails.isEmpty()) {
			return personalDetails;
		} else {
			throw new ResumeAppException("No Resume Found");
		}

	}

	@Override
	public List<PersonalDetailsDto> getAllCandidatesBasedOnExperience(int workExp) {
		List<PersonalDetails> personalDetails = personalDetailsRepository.getPersonalDetailsByExperience(workExp);
		List<PersonalDetailsDto> personalDetailsDtos = mapToDTo(personalDetails);
		if (!personalDetailsDtos.isEmpty()) {
			return personalDetailsDtos;
		} else {
			throw new ResumeAppException("Resume Not Found for workExperience " + workExp);
		}
	}

	@Override
	public List<PersonalDetailsDto> getAllCandidatesBasedOnName(String name) {
		List<PersonalDetails> personalDetails = personalDetailsRepository.getPersonalDetailsByName(name);
		List<PersonalDetailsDto> personalDetailsDtos = mapToDTo(personalDetails);
		if (!personalDetailsDtos.isEmpty()) {
			return personalDetailsDtos;
		} else {
			throw new ResumeAppException("Resume Not Found for Name " + name);
		}

	}

	@Override
	public HttpStatus deleteAllCandidatesBasedOnId(int id) {
		Optional<PersonalDetails> personalDetails = personalDetailsRepository.findById(id);
		if (personalDetails.isPresent()) {
			personalDetailsRepository.deleteById(id);
			return HttpStatus.NO_CONTENT;
		} else
			throw new ResumeAppException("RESUME NOT FOUND TO DELETE");
	}

	private List<PersonalDetailsDto> mapToDTo(List<PersonalDetails> personalDetails) {
		List<PersonalDetailsDto> personalDetailsDtos = new ArrayList<>();

		for (PersonalDetails personal : personalDetails) {
			PersonalDetailsDto personalDetailsDto = new PersonalDetailsDto();
			List<EducationDetails> educations = personal.getEducationdetails();
			List<EducationDetailsDto> educationDetailsDtos = new ArrayList<>();
			for (EducationDetails education : educations) {
				EducationDetailsDto educationDetailsDto = new EducationDetailsDto();
				educationDetailsDto.setAreaOfStudy(education.getAreaOfStudy());
				educationDetailsDto.setCollegeName(education.getCollegeName());
				educationDetailsDto.setDegree(education.getDegree());
				educationDetailsDto.seteId(education.geteId());
				educationDetailsDto.setEndDate(education.getStartDate());
				educationDetailsDto.setStartDate(education.getStartDate());
				educationDetailsDtos.add(educationDetailsDto);
			}
			List<WorkExperienceDto> workExperienceDto = new ArrayList<>();
			List<WorkExperience> workExperiences = personal.getWorkExperience();
			for (WorkExperience work : workExperiences) {
				WorkExperienceDto workExperience = new WorkExperienceDto();
				workExperience.setwId(work.getwId());
				workExperience.setStartDate(work.getStartDate());
				workExperience.setEndDate(work.getEndDate());
				workExperience.setAchievements(work.getAchievements());
				workExperience.setDesignation(work.getDesignation());
				workExperience.setJobDescription(work.getJobDescription());
				workExperience.setSkills(work.getSkills());
				workExperience.setWorkExperience(work.getWorkExperience());
				workExperienceDto.add(workExperience);
			}
			personalDetailsDto.setName(personal.getName());
			personalDetailsDto.setAddress(personal.getAddress());
			personalDetailsDto.setEmailId(personal.getEmailId());
			personalDetailsDto.setpId(personal.getpId());
			personalDetailsDto.setEducationdetails(educationDetailsDtos);
			personalDetailsDto.setPrimaryContactNumber(personal.getPrimaryContactNumber());
			personalDetailsDto.setSecondaryContactNumber(personal.getSecondaryContactNumber());
			personalDetailsDto.setWorkExperience(workExperienceDto);
			personalDetailsDtos.add(personalDetailsDto);
		}
		return personalDetailsDtos;
	}

}
