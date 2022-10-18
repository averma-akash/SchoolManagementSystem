package com.sms.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sms.application.userdetails.dbpojo.UserDetailsDbPojo;

@SpringBootApplication
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

}
