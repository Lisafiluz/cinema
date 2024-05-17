package com.cinema.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderId implements Serializable {
	
	private int userId;
	private int seatId;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof OrderId orderId)) {
			return false;
		}
		return userId == orderId.userId && seatId == orderId.seatId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(userId, seatId);
	}
}
