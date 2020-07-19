package com.application.resumeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.resumeApp.entities.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {

}
