package com.sms.application.userdetails.serviceimpl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sms.application.exception.ApplicationException;
import com.sms.application.userdetails.dao.UserDetailDao;
import com.sms.application.userdetails.dbpojo.UserDetailsDbPojo;
import com.sms.application.userdetails.pojo.RegistrationRequestPojo;
import com.sms.application.userdetails.pojo.RegistrationResponsePojo;
import com.sms.application.userdetails.service.UserDetailService;
import com.sms.application.utils.AppConstants;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	UserDetailDao dao;

	@Autowired
	PasswordEncoder encoder;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RegistrationResponsePojo userRegistration(RegistrationRequestPojo registration) throws Exception {

		RegistrationResponsePojo response = new RegistrationResponsePojo();
		if (dao.findByemailId(registration.getEmail())) {
			response.setMessage(AppConstants.ALREADY_REGISTERED);
			response.setUserId(null);
			return response;
		}
		UserDetailsDbPojo user = new UserDetailsDbPojo();
		user.setUserName(registration.getUserName());
		user.setEmailId(registration.getEmail());
		user.setUserPassword(encoder.encode(registration.getPassword()));
		user.setRole(registration.getRole());

		UserDetailsDbPojo save = dao.save(user);

		if (save.getUserId() != null) {
			response.setMessage(AppConstants.REGISTRATION_SUCCESSFUL);
			response.setUserId(save.getUserId().toString());
		} else {
			throw new ApplicationException("123","weeo");
		}

		return response;
	}

}
