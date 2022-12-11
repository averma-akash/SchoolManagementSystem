package com.sms.application.userdetails.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoResponse {
	
	private Long id;
	private String username;
	private String email;
	private String roles;

}
