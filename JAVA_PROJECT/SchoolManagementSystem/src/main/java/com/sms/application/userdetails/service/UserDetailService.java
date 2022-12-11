package com.sms.application.userdetails.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sms.application.userdetails.dbpojo.UserDetailsDbPojo;
import com.sms.application.userdetails.pojo.LoginRequest;
import com.sms.application.userdetails.pojo.RegistrationRequestPojo;
import com.sms.application.userdetails.pojo.RegistrationResponsePojo;
import com.sms.application.userdetails.pojo.UserDetailsPojo;

public interface UserDetailService {

	RegistrationResponsePojo userRegistration(@Valid RegistrationRequestPojo registration) throws Exception;

	ResponseEntity<?> userSignIn(@Valid LoginRequest loginRequest) throws Exception;
	
}
