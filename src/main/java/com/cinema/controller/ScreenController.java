package com.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cinema.ResponseWrapper;
import com.cinema.dto.ScreenDto;
import com.cinema.exception.ServiceException;
import com.cinema.service.ScreenService;

import java.util.List;

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

	@GetMapping("/get-by-movie")
	@ResponseBody
	public ResponseWrapper<ScreenDto> getScreensByMovieId(@RequestParam Integer movieId) throws ServiceException {
		List<ScreenDto> screensDto = screenService.getScreensByMovieId(movieId);
		return ResponseWrapper.success(screensDto);
	}
}
