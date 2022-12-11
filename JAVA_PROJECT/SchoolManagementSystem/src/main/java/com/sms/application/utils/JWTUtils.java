package com.sms.application.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.sms.application.userdetails.pojo.UserDetailsPojo;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTUtils {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JWTUtils.class);

	@Value("${sms.app.jwtExpirationMs}")
	private String jwtExpirationMs;

	@Value("${sms.app.jwtSecret}")
	private String jwtSecret;

	@Value("${sms.app.jwtCookieName}")
	private String jwtCookie;

	public ResponseCookie generateJwtToken(UserDetailsPojo principal) throws ParseException {

		String jwt = Jwts.builder().setSubject(principal.getUsername()).setIssuedAt(new Date())
				.setExpiration(Date.from(ZonedDateTime.now().plusMinutes(5).toInstant()))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/").maxAge(24 * 60 * 60).httpOnly(true)
				.build();
		return cookie;
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

	public String getJwtFromCookies(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, jwtCookie);
		if (cookie != null) {
			return cookie.getValue();
		} else {
			return null;
		}
	}

}
