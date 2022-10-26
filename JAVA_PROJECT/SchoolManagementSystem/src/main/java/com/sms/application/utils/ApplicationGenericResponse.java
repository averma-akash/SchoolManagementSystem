package com.sms.application.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplicationGenericResponse<D> {
	
	public enum Status {
		SUCCESS, 
		FAIL,
		ERROR,
		FORBIDDEN,
		MAICIOUSCODE;
	}
	
	private Status status;
	private D data;
	
	public static final <D> ApplicationGenericResponse<D> success(D data) {
		return new ApplicationGenericResponse<>(Status.SUCCESS, data);
		
	}
	public static final <D> ApplicationGenericResponse<D> fail(D data) {
		return new ApplicationGenericResponse<>(Status.FAIL, data);
		
	}

}
