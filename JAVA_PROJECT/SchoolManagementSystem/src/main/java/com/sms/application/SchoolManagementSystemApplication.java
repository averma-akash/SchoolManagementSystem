package com.sms.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sms.application.userdetails.dbpojo.UserDetailsDbPojo;

@SpringBootApplication
@EnableWebMvc
//public class SchoolManagementSystemApplication implements CommandLineRunner {
public class SchoolManagementSystemApplication {

//	 @Autowired
//	 private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementSystemApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//	
//		String sql = "SELECT * FROM USER_DETAILS";
//        
//        List<UserDetailsDbPojo> students = jdbcTemplate.query(sql,
//                BeanPropertyRowMapper.newInstance(UserDetailsDbPojo.class));
//         
//        System.out.println("DB COnnection succesful");
//        students.forEach(System.out :: println);
//		
//	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}

}
