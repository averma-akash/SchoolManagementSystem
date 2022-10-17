package com.sms.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//        students.forEach(System.out :: println);
//		
//	}

}
