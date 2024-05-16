package com.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cinema.dto.HallDto;
import com.cinema.model.Hall;

@Mapper
public interface HallMapper {
	HallMapper INSTANCE = Mappers.getMapper(HallMapper.class);
	
	HallDto hallToHallDto(Hall hall);
	
	Hall hallDtoToHall(HallDto dto);
}
