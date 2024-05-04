package com.cinema.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.dto.SeatDto;
import com.cinema.mapper.SeatMapper;
import com.cinema.model.Seat;
import com.cinema.repository.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService{
	private final SeatRepository seatRepository;
	
	@Autowired
	public SeatServiceImpl(SeatRepository seatRepository) {
		this.seatRepository = seatRepository;
	}
	
	@Override
	public Set<Seat> getSeatsByScreenId(int screenId) {
		return seatRepository.getAllByScreenId(screenId);
	}
	
	@Override
	public Seat creteSeat(Seat seat) {
		return seatRepository.save(seat);
	}
	
	@Override
	public Map<Integer, SeatDto> getSeatIdToSeatDto(Set<Integer> seatIds) {
		List<Seat> seats = seatRepository.findAllById(seatIds);
		return seats.stream().map(SeatMapper.INSTANCE::seatToSeatDto).collect(Collectors.toMap((SeatDto::getSeatId), b->b));
	}
}
