package com.sms.application.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationExceptionDto {
	
	private String messageCode;
	private String messageDescription;

}
