package com.sms.application.userdetails.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sms.application.userdetails.pojo.RegistrationRequestPojo;
import com.sms.application.userdetails.pojo.RegistrationResponsePojo;
import com.sms.application.userdetails.service.UserDetailService;
import com.sms.application.utils.AppConstants;

@RestController
public class UserDetailsController {
	
	@Autowired
	UserDetailService userDetails;
	
	@PostMapping(name = "/registration")
	ResponseEntity<?> userRegitration(@Valid @RequestBody RegistrationRequestPojo registration) throws Exception{
		
		RegistrationResponsePojo userRegistration = userDetails.userRegistration(registration);
		if(userRegistration.getMessage().equalsIgnoreCase(AppConstants.ALREADY_REGISTERED)) {
			return ResponseEntity.badRequest().body(userRegistration);
		}
		return null;
	}

}
