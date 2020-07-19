package com.application.resumeApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.resumeApp.dto.PersonalDetailsDto;
import com.application.resumeApp.entities.PersonalDetails;
import com.application.resumeApp.exception.ResumeAppException;
import com.application.resumeApp.service.ResumeService;

@RestController
@RequestMapping("/")
public class ResumeController {

	@Autowired
	ResumeService resumeService;

	public static final Logger LOGGER = LoggerFactory.getLogger(ResumeController.class);

	/**
	 * Method to return all Resumes
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping("/getAllResume")
	public ResponseEntity<Object> getAllCandidates() {
		try {
			LOGGER.info("INTO METHOD TRYING TO GET RESUME");
			List<PersonalDetails> personal = resumeService.getAllCandidates();
			return new ResponseEntity<>(personal, HttpStatus.OK);
		} catch (ResumeAppException ex) {
			LOGGER.error("RESUME NOT FOUND");
			return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Method to add a resume
	 * 
	 * @param personalDetails
	 * @return
	 */
	@PostMapping("/addResume")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> addResume(@RequestBody PersonalDetailsDto personalDetails) {
		try {
			LOGGER.info("INTO METHOD TRYING TO ADD RESUME");
			PersonalDetails personal = resumeService.addResume(personalDetails);
			return new ResponseEntity<>(personal, HttpStatus.OK);
		} catch (ResumeAppException ex) {
			LOGGER.error("RESUME ALREADY FOUND FOR CONTACT NUMBER" + personalDetails.getPrimaryContactNumber());
			return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.ALREADY_REPORTED);
		}

	}

	/**
	 * Method to get Experiencedd Resume
	 * 
	 * @param workExp
	 * @return
	 */
	@GetMapping("/getExperiencedResume/{workExp}")
	public ResponseEntity<Object> getAllCandidatesBasedOnExperience(@PathVariable int workExp) {
		try {
			LOGGER.info("INTO METHOD TRYING TO FIND RESUME WITH WORK_EXPERIENCE : {}", workExp);
			List<PersonalDetailsDto> personalDetailsDto = resumeService.getAllCandidatesBasedOnExperience(workExp);
			return new ResponseEntity<>(personalDetailsDto, HttpStatus.OK);
		} catch (ResumeAppException ex) {
			LOGGER.error("RESUME WITH WORK_EXPERIENCE:{} NOT FOUND", workExp);
			return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Method to get all Resume by name
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping("/getResumeByName/{name}")
	public ResponseEntity<Object> getAllResumeByName(@PathVariable String name) {
		try {
			LOGGER.info("INTO METHOD TRYING TO FIND RESUME WITH NAME : {}", name);
			List<PersonalDetailsDto> personalDetailsDto = resumeService.getAllCandidatesBasedOnName(name);
			return new ResponseEntity<>(personalDetailsDto, HttpStatus.OK);
		} catch (ResumeAppException ex) {
			LOGGER.error("RESUME WITH NAME:{} NOT FOUND", name);
			return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Method to delete All candidates based on ID
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteById/{id}")
	public HttpStatus deleteAllCandidatesBasedOnId(@PathVariable int id) {
		LOGGER.debug("TRYING TO DELETE PROFILE WITH PID : {}", id);
		try {
			resumeService.deleteAllCandidatesBasedOnId(id);
			return HttpStatus.NO_CONTENT;
		} catch (ResumeAppException e) {
			LOGGER.error("RESUME NOT FOUND FOR ID : {}", id);
			return HttpStatus.NOT_FOUND;
		}

	}

}
