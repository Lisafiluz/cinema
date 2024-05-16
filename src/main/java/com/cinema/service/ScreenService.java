package com.cinema.service;

import com.cinema.dto.ScreenDto;
import com.cinema.exception.ServiceException;
import com.cinema.request.AddScreenRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ScreenService {
	
	ScreenDto getScreen(Integer screenId) throws ServiceException;

    List<ScreenDto> getScreensByMovieId(Integer movieId) throws ServiceException;
	
	Map<Integer, ScreenDto> getScreenIdToScreenDto(Set<Integer> screenIds);
	
	List<ScreenDto> getAllScreens();
	
	void createScreen(AddScreenRequest addScreenRequest) throws ServiceException;
}
