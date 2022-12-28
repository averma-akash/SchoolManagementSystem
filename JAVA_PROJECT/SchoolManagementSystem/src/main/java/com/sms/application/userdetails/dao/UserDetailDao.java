package com.sms.application.userdetails.dao;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.application.userdetails.dbpojo.UserDetailsDbPojo;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetailsDbPojo, BigDecimal> {

	UserDetailsDbPojo findByemailId(String email);
}
