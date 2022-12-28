package com.sms.application.userdetails.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sms.application.userdetails.dbpojo.NotificationDbPojo;
import com.sms.application.userdetails.dbpojo.UserDetailsDbPojo;
import com.sms.application.userdetails.pojo.LoginRequest;
import com.sms.application.userdetails.pojo.RegistrationRequestPojo;
import com.sms.application.userdetails.pojo.RegistrationResponsePojo;
import com.sms.application.userdetails.pojo.UserDetailsPojo;
import com.sms.application.utils.ApplicationGenericResponse;

public interface UserDetailService {

	RegistrationResponsePojo userRegistration(@Valid RegistrationRequestPojo registration) throws Exception;

	ResponseEntity<?> userSignIn(@Valid LoginRequest loginRequest) throws Exception;

	List<NotificationDbPojo> getNotification(String userRole) throws Exception;
	
}
