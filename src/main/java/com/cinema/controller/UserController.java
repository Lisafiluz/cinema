package com.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.ResponseWrapper;
import com.cinema.dto.OrderDto;
import com.cinema.exception.ServiceException;
import com.cinema.security.services.UserDetailsImpl;
import com.cinema.service.OrderService;

@RestController
@RequestMapping("user/")
public class UserController {
	
	private final OrderService orderService;
	
	@Autowired
	public UserController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/orders")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ADMIN')")
	public ResponseWrapper<OrderDto> getOrders() throws ServiceException {
		Integer userId = UserDetailsImpl.getUser().getId();
		List<OrderDto> orders = orderService.getOrdersByUser(userId);
		return ResponseWrapper.success(orders);
	}
}
