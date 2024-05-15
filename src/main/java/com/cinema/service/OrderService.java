package com.cinema.service;

import java.util.List;

import com.cinema.ResponseWrapper;
import com.cinema.dto.OrderDto;
import com.cinema.exception.ServiceException;
import com.cinema.request.OrderRequest;

public interface OrderService {
	
	ResponseWrapper<Void> placeOrder(OrderRequest orderRequest) throws ServiceException;
	
	List<OrderDto> getOrdersByUser(Integer userId);
	
	void cancelSeat(Integer seatId) throws ServiceException;
	
	void cancelOrder(Integer orderId) throws ServiceException;
	
	List<OrderDto> getAllOrders();
}
