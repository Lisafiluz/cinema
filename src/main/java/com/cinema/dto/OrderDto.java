package com.cinema.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
	
	private int userId;
	private ScreenDto screen;
	private List<SeatDto> seats;
	private Long date;
	private int quantity;
	private int orderId;
}
