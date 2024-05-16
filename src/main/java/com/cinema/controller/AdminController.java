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
import com.cinema.dto.HallDto;
import com.cinema.dto.MovieDto;
import com.cinema.dto.OrderDto;
import com.cinema.dto.ScreenDto;
import com.cinema.exception.ServiceException;
import com.cinema.security.services.UserDetailsImpl;
import com.cinema.service.HallService;
import com.cinema.service.MovieService;
import com.cinema.service.OrderService;
import com.cinema.service.ScreenService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private final OrderService orderService;
	private final MovieService movieService;
	private final ScreenService screenService;
	private final HallService hallService;
	
	@Autowired
	public AdminController(OrderService orderService, MovieService movieService, ScreenService screenService, HallService hallService) {
		this.orderService = orderService;
		this.movieService = movieService;
		this.screenService = screenService;
		this.hallService = hallService;
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
	
	@GetMapping("/screens")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseWrapper<ScreenDto> getScreens() throws ServiceException {
		List<ScreenDto> screens = screenService.getAllScreens();
		return ResponseWrapper.success(screens);
	}
	
	@GetMapping("/halls")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseWrapper<HallDto> getHalls() throws ServiceException {
		List<HallDto> halls = hallService.getAllHalls();
		return ResponseWrapper.success(halls);
	}
}
