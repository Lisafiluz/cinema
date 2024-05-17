package com.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cinema.dto.ScreenDto;
import com.cinema.model.Screen;

@Mapper
public interface ScreenMapper {
	
	ScreenMapper INSTANCE = Mappers.getMapper(ScreenMapper.class);
	
	ScreenDto screenToScreenDto(Screen screen);
	
	Screen screenDtoToScreen(ScreenDto screenDto);
	
}
