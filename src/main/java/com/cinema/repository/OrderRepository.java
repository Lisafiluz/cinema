package com.cinema.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinema.model.Order;
import com.cinema.model.OrderId;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {
	List<Order> getOrdersByUserId(Integer userId);
	
	@Query(value = "SELECT MAX(o.orderId) FROM Order o")
	Integer getCurrentOrderId();
	
	Order findBySeatId(Integer seatId);
	
	@Transactional
	void removeOrderByOrderId(Integer orderId);
	
	@Transactional
	void removeOrderBySeatId(Integer seatId);
	
	List<Order> findAllByOrderId(int orderId);
	
	Optional<Order> findByOrderId(Integer orderId);
}
