package com.cinema.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OrderId.class)
public class Order {
	
	@Id
	@Column(name = "user_id", nullable = false)
	@JoinTable(name = "users", joinColumns = @JoinColumn(name = "user_id"))
	private int userId;
	
	@Id
	@Column(name = "seat_id", nullable = false)
	@JoinTable(name = "seats", joinColumns = @JoinColumn(name = "seat_id"))
	private int seatId;
	
	@Column(name = "screen_id", nullable = false)
	@JoinTable(name = "screens", joinColumns = @JoinColumn(name = "screen_id"))
	private int screenId;
	
	@Column(name = "order_time", nullable = false)
	private Long date;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@Column(name = "order_id", nullable = false)
	private int orderId;
	
	public Order(Integer userId, int seatId, int screenId, long orderDate, int size) {
		this.userId = userId;
		this.seatId = seatId;
		this.screenId = screenId;
		
	}
	
	@Override
	public String toString() {
		return "Order{" +
					 "userId=" + userId +
					 ", seatId=" + seatId +
					 ", screenId=" + screenId +
					 ", date=" + date +
					 ", quantity=" + quantity +
					 ", orderId=" + orderId +
					 '}';
	}
}
