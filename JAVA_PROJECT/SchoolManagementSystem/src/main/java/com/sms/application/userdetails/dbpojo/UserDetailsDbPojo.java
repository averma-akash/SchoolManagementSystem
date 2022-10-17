package com.sms.application.userdetails.dbpojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_DETAILS")
@Getter
@Setter
public class UserDetailsDbPojo {
	
	@Id
	@Column(name = "USER_ID")
	private BigDecimal userId;
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	@Column(name = "ROLE")
	private String role;
	@Column(name = "EMAIL_ID")
	private String emailId;
	@Column(name = "USER_NAME")
	private String userName;

}
