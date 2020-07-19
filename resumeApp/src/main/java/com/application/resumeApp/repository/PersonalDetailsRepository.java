package com.application.resumeApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.resumeApp.entities.PersonalDetails;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Integer> {
	@Query(name = "getResumeByName")
	List<PersonalDetails> getPersonalDetailsByExperience(int workExp);

	@Query(name = "getResumeByExperience")
	List<PersonalDetails> getPersonalDetailsByName(String name);

	Optional<PersonalDetails> findByPrimaryContactNumber(String phoneNum);

}
