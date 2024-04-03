package com.cinema.service;

import com.cinema.dto.UserDto;
import com.cinema.exception.ServiceException;
import com.cinema.request.LoginRequest;
import com.cinema.request.SignupRequest;
import com.cinema.response.JwtResponse;

public interface UserService {
	
	UserDto registerUser(SignupRequest signUpRequest) throws ServiceException;
	
	JwtResponse authenticateUser(LoginRequest loginRequest);
}
