package com.cinema.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cinema.dto.UserDto;
import com.cinema.enums.ERole;
import com.cinema.enums.ServiceResponseCode;
import com.cinema.exception.ServiceException;
import com.cinema.exception.ServiceRuntimeException;
import com.cinema.model.Role;
import com.cinema.model.User;
import com.cinema.repository.RoleRepository;
import com.cinema.repository.UserRepository;
import com.cinema.request.LoginRequest;
import com.cinema.request.SignupRequest;
import com.cinema.response.JwtResponse;
import com.cinema.security.jwt.JwtUtils;
import com.cinema.security.services.UserDetailsImpl;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final AuthenticationManager authenticationManager;
	
	private final RoleRepository roleRepository;
	
	private final PasswordEncoder encoder;
	
	private final JwtUtils jwtUtils;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
												 RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}
	
	@Override
	public UserDto registerUser(SignupRequest signUpRequest) throws ServiceException {
		if (signUpRequest.getUsername() == null || signUpRequest.getPassword() == null) {
			throw new ServiceException(ServiceResponseCode.ERROR_INVALID_INPUT, "Username or Password cannot be empty!");
		}
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new ServiceException(ServiceResponseCode.ERROR_BAD_REQUEST, "Error: Username is already taken!");
		}
		
		User user = new User(signUpRequest.getUsername(),
												 encoder.encode(signUpRequest.getPassword()));
		
		Set<ERole> strRoles = Set.of(ERole.ROLE_USER);
		Set<Role> roles = new HashSet<>();
		
		strRoles.forEach(role -> {
			Role userRole = roleRepository.findByName(role)
																		.orElseThrow(() -> new ServiceRuntimeException(ServiceResponseCode.ERROR_NOT_FOUND, "Role is not found!"));
			roles.add(userRole);
		});
		
		user.setRoles(roles);
		userRepository.save(user);
		
		return new UserDto(user.getId(), user.getUsername());
	}
	
	@Override
	public JwtResponse authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles);
	}
}
