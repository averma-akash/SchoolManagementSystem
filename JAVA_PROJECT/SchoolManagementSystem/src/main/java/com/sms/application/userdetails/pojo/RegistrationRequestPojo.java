package com.sms.application.userdetails.pojo;

import javax.validation.constraints.*;

import lombok.*;

@Getter
@Setter
public class RegistrationRequestPojo {
	
	@NotNull
	private String userName;
	@NotBlank
	@Email
	private String email;
	@NotBlank
    @Size(min = 8, max = 10)
	private String password;
	@NotBlank
	private String role;

}
