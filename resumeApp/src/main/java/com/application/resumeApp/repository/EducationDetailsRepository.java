package com.application.resumeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.resumeApp.entities.EducationDetails;

public interface EducationDetailsRepository extends JpaRepository<EducationDetails, Integer> {

}
