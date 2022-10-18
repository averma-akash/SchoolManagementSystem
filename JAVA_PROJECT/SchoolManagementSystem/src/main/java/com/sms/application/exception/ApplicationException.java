package com.sms.application.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ApplicationException extends Exception {
	
	private String errorCode;
	private String errorDescription;
	
	public ApplicationException(String errorCode, Throwable t) {
		ApplicationExceptionDto dto = getExceptionDetail(errorCode, t);
		this.setErrorCode(errorCode);
		this.setErrorDescription(dto.getMessageDescription());
		this.initCause(t);
	}
	
	public final ApplicationExceptionDto getExceptionDetail(String errorCode, Throwable t) {
		
		ApplicationExceptionDto dto = new ApplicationExceptionDto();
		ApplicationExceptionConfig config = ApplicationExceptionConfig.getInstance();
		
		if(config != null) {
			dto.setMessageCode(errorCode);
			dto.setMessageDescription(config.get(errorCode));
		}
		
		t.printStackTrace();
		return dto;
		
	}

}
