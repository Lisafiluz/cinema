package com.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.ResponseWrapper;
import com.cinema.dto.ScreenDto;
import com.cinema.exception.ServiceException;
import com.cinema.service.ScreenService;

@RestController
@RequestMapping("screen/")
public class ScreenController {
	
	private final ScreenService screenService;
	
	@Autowired
	public ScreenController(ScreenService screenService) {
		this.screenService = screenService;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseWrapper<ScreenDto> getScreen(@PathVariable Integer id) throws ServiceException {
		ScreenDto screenDto = screenService.getScreen(id);
		return ResponseWrapper.success(screenDto);
	}
}
