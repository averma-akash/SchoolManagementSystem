package com.sms.application.userdetails.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sms.application.userdetails.pojo.LoginRequest;
import com.sms.application.userdetails.pojo.RegistrationRequestPojo;
import com.sms.application.userdetails.pojo.RegistrationResponsePojo;
import com.sms.application.userdetails.service.UserDetailService;
import com.sms.application.utils.AppConstants;
import com.sms.application.utils.ApplicationGenericResponse;

@RestController
public class UserDetailsController {
	
	@Autowired
	UserDetailService userDetails;
	
	@PostMapping(path = "/registration")
	ApplicationGenericResponse<RegistrationResponsePojo> userRegitration(@Valid @RequestBody RegistrationRequestPojo registration) throws Exception{
		
		RegistrationResponsePojo userRegistration = userDetails.userRegistration(registration);
		if(userRegistration.getMessage().equalsIgnoreCase(AppConstants.ALREADY_REGISTERED)) {
			//return ResponseEntity.badRequest().body(userRegistration);
			return ApplicationGenericResponse.fail(userRegistration);
		}
		//return ResponseEntity.ok(userRegistration);
		return ApplicationGenericResponse.success(userRegistration);
	}
	
	@PostMapping(path = "/signin")
	  public ResponseEntity<?> userSignIn(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
		
		return userDetails.userSignIn(loginRequest);
	  }

}
