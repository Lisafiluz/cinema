package com.cinema.dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreenDto {
	
	private Integer screenId;
	private MovieDto movie;
	private HallDto hall;
	private Date date;
	private Set<SeatDto> seats;
	
}
