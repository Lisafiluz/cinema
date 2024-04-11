package com.cinema.service;

import com.cinema.dto.ScreenDto;
import com.cinema.exception.ServiceException;

public interface ScreenService {
	
	ScreenDto getScreen(Integer screenId) throws ServiceException;
}
