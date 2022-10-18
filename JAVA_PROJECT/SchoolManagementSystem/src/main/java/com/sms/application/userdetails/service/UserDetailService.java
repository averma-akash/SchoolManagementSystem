package com.sms.application.userdetails.service;

import javax.validation.Valid;

import com.sms.application.userdetails.pojo.RegistrationRequestPojo;
import com.sms.application.userdetails.pojo.RegistrationResponsePojo;

public interface UserDetailService {

	RegistrationResponsePojo userRegistration(@Valid RegistrationRequestPojo registration) throws Exception;

}
