package com.hm.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

//	@Value("${db.password}")
//	private String dbPassword;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@PostConstruct
//	public void checkPassword() {
//
//		System.out.println("Resolved Database Password:==========================> " + dbPassword);
//	}
}
