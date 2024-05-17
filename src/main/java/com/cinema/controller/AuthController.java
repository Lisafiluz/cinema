package com.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.ResponseWrapper;
import com.cinema.dto.UserDto;
import com.cinema.exception.ServiceException;
import com.cinema.request.LoginRequest;
import com.cinema.request.SignupRequest;
import com.cinema.response.JwtResponse;
import com.cinema.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final UserService userService;
	
	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/signin")
	public ResponseWrapper<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		JwtResponse jwtResponse = userService.authenticateUser(loginRequest);
		return ResponseWrapper.success(jwtResponse);
	}
	
	@PostMapping("/signup")
	public ResponseWrapper<UserDto> registerUser(@RequestBody SignupRequest signUpRequest) throws ServiceException {
		UserDto userDto = userService.registerUser(signUpRequest);
		return ResponseWrapper.success(userDto);
	}
}
