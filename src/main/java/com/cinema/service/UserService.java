package com.cinema.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cinema.dto.UserDto;
import com.cinema.model.User;
import com.cinema.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserDto getUser(Integer id) {
		//validation
		User user = userRepository.findById(id).orElse(null);
//		User users = null;
		if (user == null) {
			return null; //maybe exception
		}
		
		return mapUserToUserDto(user);
	}
	
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::mapUserToUserDto).collect(Collectors.toList());
	}
	
//	public void saveUser(User users) {
//		users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
//		userRepository.save(users);
//	}
	
	
	
	private UserDto mapUserToUserDto(User user) {
		UserDto res = new UserDto();
		res.setId(user.getId());
		res.setUsername(user.getUsername());
		return res;
	}
}
