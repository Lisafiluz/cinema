package com.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cinema.dto.SeatDto;
import com.cinema.model.Seat;

@Mapper
public interface SeatMapper {
	
	SeatMapper INSTANCE = Mappers.getMapper(SeatMapper.class);
	
	SeatDto seatToSeatDto(Seat seat);
	
	Seat seatDtoToSeat(SeatDto seatDto);
}
