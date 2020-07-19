package com.application.resumeApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class checks the health status of home Controller
 * 
 * @author M1046841
 *
 */

@RestController
@RequestMapping("/")
public class HealthCheckController {

	public static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

	/*
	 * HealthCheck URL to check application up and running.
	 */
	@GetMapping(value = "healthCheck")
	public String healthCheck() {
		logger.info("Resume Application is Running....");
		return "Resume Application is Running....";
	}

}
