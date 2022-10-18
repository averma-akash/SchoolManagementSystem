package com.sms.application.userdetails.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.application.userdetails.dbpojo.UserDetailsDbPojo;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetailsDbPojo, BigDecimal> {

	Boolean findByemailId(String email);

}
