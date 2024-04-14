package com.cinema.service;

import com.cinema.dto.ScreenDto;
import com.cinema.exception.ServiceException;

import java.util.List;

public interface ScreenService {
	
	ScreenDto getScreen(Integer screenId) throws ServiceException;

    List<ScreenDto> getScreensByMovieId(Integer movieId) throws ServiceException;
}
