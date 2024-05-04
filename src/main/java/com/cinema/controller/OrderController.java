package com.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.ResponseWrapper;
import com.cinema.exception.ServiceException;
import com.cinema.request.OrderRequest;
import com.cinema.service.OrderService;

@RestController
@RequestMapping("order/")
public class OrderController {
	
	private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping()
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ADMIN')")
	public ResponseWrapper<Void> placeOrder(@RequestBody OrderRequest orderRequest) throws ServiceException {
		ResponseWrapper<Void> res = orderService.placeOrder(orderRequest);
		return res;
	}
	
}
