package com.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinema.model.Order;
import com.cinema.model.OrderId;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {

}
