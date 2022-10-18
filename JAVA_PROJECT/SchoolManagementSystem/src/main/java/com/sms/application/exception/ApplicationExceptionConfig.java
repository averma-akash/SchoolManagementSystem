package com.sms.application.exception;

import java.io.InputStream;
import java.util.Properties;

import com.sms.application.utils.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ApplicationExceptionConfig {
	
	private static ApplicationExceptionConfig config = new ApplicationExceptionConfig();
	private final Properties properties = new Properties();
	
	private ApplicationExceptionConfig() {
		try(InputStream is = ApplicationExceptionConfig.class.getResourceAsStream("/applicationException.properties")) {
			if(is == null)
				throw new IllegalArgumentException(AppConstants.APPLICATION_EXCEPTION_PROPERTIES_NOT_FOUND);
			properties.load(is);
		}catch (Exception e) {
			log.error("Error : ", this.getClass(), e.getMessage());
			e.initCause(e);
		}
	}
	
	public static ApplicationExceptionConfig getInstance() {
		return config;
	}
	
	public String get(String key) {
		if(properties == null || key ==null)
			return null;
		return (String) properties.get(key);
	}

}
