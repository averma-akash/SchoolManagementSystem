package com.sms.application.userdetails.dbpojo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER_DETAILS",uniqueConstraints = {
        @UniqueConstraint(columnNames = "USER_ID"),
        @UniqueConstraint(columnNames = "EMAIL_ID")
    })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDbPojo implements Serializable {
	
	private static final long serialVersionUID = -5506469932248744738L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
