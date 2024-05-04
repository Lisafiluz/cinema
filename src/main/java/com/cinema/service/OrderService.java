package com.cinema.service;

import com.cinema.ResponseWrapper;
import com.cinema.exception.ServiceException;
import com.cinema.request.OrderRequest;

public interface OrderService {
	
	ResponseWrapper<Void> placeOrder(OrderRequest orderRequest) throws ServiceException;
}
