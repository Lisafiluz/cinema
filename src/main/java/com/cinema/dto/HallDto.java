package com.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallDto {
	
	private Integer hallId;
	private String name;
	private Integer rows;
	private Integer cols;
}
