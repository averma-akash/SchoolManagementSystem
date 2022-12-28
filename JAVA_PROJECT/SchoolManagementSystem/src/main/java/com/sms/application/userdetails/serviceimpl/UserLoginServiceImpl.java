package com.sms.application.userdetails.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.application.userdetails.dao.UserDetailDao;
import com.sms.application.userdetails.dbpojo.UserDetailsDbPojo;
import com.sms.application.userdetails.pojo.UserDetailsPojo;

@Service
public class UserLoginServiceImpl implements UserDetailsService{
	@Autowired
	UserDetailDao dao;

  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  UserDetailsDbPojo user = dao.findByemailId(username);
	  if(user == null)
        throw new UsernameNotFoundException("User Not Found with username: " + username);
	  
	  UserDetailsPojo users = new UserDetailsPojo();
	  users.setEmail(user.getEmailId());
	  users.setId(user.getUserId());
	  users.setRole(user.getRole());
	  users.setPassword(user.getUserPassword());
	  users.setUsername(user.getUserName());

    return users;
  }

}
