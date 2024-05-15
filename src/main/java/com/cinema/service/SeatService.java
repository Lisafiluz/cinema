package com.cinema.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cinema.dto.SeatDto;
import com.cinema.exception.ServiceException;
import com.cinema.model.Seat;

public interface SeatService {
	
	Set<Seat> getSeatsByScreenId(int screenId);
	
	Seat creteSeat(Seat seat);
	
	Map<Integer, SeatDto> getSeatIdToSeatDto(Set<Integer> seatIds);
	
	void deleteSeats(List<Integer> seatIds) throws ServiceException;
}
