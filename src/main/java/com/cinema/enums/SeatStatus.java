package com.cinema.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum SeatStatus {
	AVAILABLE("available"),
	BOOKED("booked"),
	SAVED("saved");
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "SeatStatus{" +
					 "name='" + name + '\'' +
					 '}';
	}
}
