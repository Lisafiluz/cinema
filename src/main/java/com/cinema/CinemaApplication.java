package com.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class CinemaApplication {
	
//	private final JdbcTemplate jdbcTemplate;
//
//	@Autowired
//	public CinemaApplication(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}
	
}
