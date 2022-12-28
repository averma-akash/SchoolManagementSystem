package com.sms.application.userdetails.serviceimpl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sms.application.exception.ApplicationException;
import com.sms.application.userdetails.dao.UserDetailDao;
import com.sms.application.userdetails.dbpojo.UserDetailsDbPojo;
import com.sms.application.userdetails.pojo.LoginRequest;
import com.sms.application.userdetails.pojo.RegistrationRequestPojo;
import com.sms.application.userdetails.pojo.RegistrationResponsePojo;
import com.sms.application.userdetails.pojo.UserDetailsPojo;
import com.sms.application.userdetails.pojo.UserInfoResponse;
import com.sms.application.userdetails.service.UserDetailService;
import com.sms.application.utils.AppConstants;
import com.sms.application.utils.JWTUtils;

@Service
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	UserDetailDao dao;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTUtils jwtUtils;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RegistrationResponsePojo userRegistration(RegistrationRequestPojo registration) throws Exception {

		RegistrationResponsePojo response = new RegistrationResponsePojo();
		if (dao.findByemailId(registration.getEmail()) != null) {
			response.setMessage(AppConstants.ALREADY_REGISTERED);
			response.setUserId(null);
			return response;
		}
		UserDetailsDbPojo user = new UserDetailsDbPojo();
		user.setUserName(registration.getUserName());
		user.setEmailId(registration.getEmail());
		user.setUserPassword(encoder.encode(registration.getPassword()));
		user.setRole(registration.getRole().toUpperCase());

		UserDetailsDbPojo save = dao.save(user);

		if (save.getUserId() != null) {
			response.setMessage(AppConstants.REGISTRATION_SUCCESSFUL);
			response.setUserId(save.getUserId().toString());
		} else {
			throw new ApplicationException("123","weeo");
		}

		return response;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseEntity<UserInfoResponse> userSignIn(@Valid LoginRequest loginRequest) throws Exception {
		
//		UserDetailsDbPojo user = dao.findByUserName(loginRequest.getUsername())
//		        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + loginRequest.getUsername()));
		
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		UserDetailsPojo principal = (UserDetailsPojo) auth.getPrincipal();
		ResponseCookie jwtCookie = jwtUtils.generateJwtToken(principal);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
		        .body(new UserInfoResponse(principal.getId(),
		        		principal.getUsername(),
		        		principal.getEmail(), principal.getRole()));
	}

}
