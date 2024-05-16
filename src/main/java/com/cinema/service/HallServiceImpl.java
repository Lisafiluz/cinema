package com.cinema.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.dto.HallDto;
import com.cinema.mapper.HallMapper;
import com.cinema.model.Hall;
import com.cinema.repository.HallRepository;

@Service
public class HallServiceImpl implements HallService{
	
	private final HallRepository hallRepository;
	
	@Autowired
	public HallServiceImpl(HallRepository hallRepository) {
		this.hallRepository = hallRepository;
	}
	
	@Override
	public List<HallDto> getAllHalls() {
		List<Hall> halls = hallRepository.findAll();
		return halls.stream().map(HallMapper.INSTANCE::hallToHallDto).collect(Collectors.toList());
	}
}
