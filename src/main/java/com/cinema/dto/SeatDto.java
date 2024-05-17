package com.cinema.dto;

import com.cinema.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
	
	private int seatId;
	private int screenId;
	private int row;
	private int seatNumber;
	private SeatStatus status;
}
