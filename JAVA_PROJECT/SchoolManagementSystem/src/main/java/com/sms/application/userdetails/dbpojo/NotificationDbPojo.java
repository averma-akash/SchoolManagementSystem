package com.sms.application.userdetails.dbpojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "USER_NOTIFICATION")
public class NotificationDbPojo implements Serializable {
	
	private static final long serialVersionUID = 1223504816934679855L;
	
	@Column(name = "NOTIFICATION_ID")
	@Id
	private Integer notificationId;
	
	@Column(name = "NOTIFICATION_SUBJECT")
	private String notificationSubject;
	
	@Column(name = "NOTIFICATION_DETAIL")
	private String notificationDetail;
	
	@Column(name = "USER_TYPE")
	private String userType;
	
	@Column(name = "NOTIFICATION_DATE")
	private Date notificationDate;

}
