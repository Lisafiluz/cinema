package com.cinema.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.dto.ScreenDto;
import com.cinema.mapper.ScreenMapper;
import com.cinema.model.Screen;
import com.cinema.repository.ScreenRepository;

@Service
public class ScreenServiceImpl implements ScreenService {
	
	private final ScreenRepository screenRepository;
	
	@Autowired
	public ScreenServiceImpl(ScreenRepository screenRepository) {
		this.screenRepository = screenRepository;
	}
	
	@Override
	public ScreenDto getScreen(Integer screenId) {
		Optional<Screen> screen = screenRepository.findById(screenId);
		return ScreenMapper.INSTANCE.screenToScreenDto(screen.orElse(null));
	}
}
