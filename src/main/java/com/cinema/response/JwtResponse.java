package com.cinema.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {
	
	private String jwt;
	private Integer id;
	private String username;
	private List<String> roles;
	
}
