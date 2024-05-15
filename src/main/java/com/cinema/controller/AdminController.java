package com.cinema.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.ResponseWrapper;
import com.cinema.dto.MovieDto;
import com.cinema.dto.OrderDto;
import com.cinema.exception.ServiceException;
import com.cinema.security.services.UserDetailsImpl;
import com.cinema.service.MovieService;
import com.cinema.service.OrderService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private final OrderService orderService;
	private final MovieService movieService;
	
	@Autowired
	public AdminController(OrderService orderService, MovieService movieService) {
		this.orderService = orderService;
		this.movieService = movieService;
	}
	
	@GetMapping("/is-admin")
	public boolean isAdmin() throws ServiceException {
		Set<String> authorities = UserDetailsImpl.getUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
		return authorities.contains("ROLE_ADMIN");
	}
	
	@GetMapping("/orders")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseWrapper<OrderDto> getOrders() throws ServiceException {
		List<OrderDto> orders = orderService.getAllOrders();
		return ResponseWrapper.success(orders);
	}
	
	@GetMapping("/movies")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseWrapper<MovieDto> getMovies() throws ServiceException {
		List<MovieDto> movies = movieService.getAllMovies();
		return ResponseWrapper.success(movies);
	}
}
