package com.sms.application.userdetails.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.application.userdetails.dbpojo.NotificationDbPojo;

@Repository
public interface NotificationDao extends JpaRepository<NotificationDbPojo, String>{
	
	List<NotificationDbPojo> findByuserType(String userType);

}
