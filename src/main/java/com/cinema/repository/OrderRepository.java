package com.cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinema.model.Movie;
import com.cinema.model.Order;
import com.cinema.model.OrderId;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {
	List<Order> getOrdersByUserId(Integer userId);
	
	@Query(value = "SELECT MAX(o.orderId) FROM Order o")
	Integer getCurrentOrderId();
}
