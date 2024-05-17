package com.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.ResponseWrapper;
import com.cinema.dto.ScreenDto;
import com.cinema.exception.ServiceException;
import com.cinema.request.AddScreenRequest;
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
	
	@GetMapping("/get-by-movie")
	@ResponseBody
	public ResponseWrapper<ScreenDto> getScreensByMovieId(@RequestParam Integer movieId) throws ServiceException {
		List<ScreenDto> screensDto = screenService.getScreensByMovieId(movieId);
		return ResponseWrapper.success(screensDto);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseWrapper<Void> addScreen(@RequestBody AddScreenRequest addScreenRequest) throws ServiceException {
		screenService.createScreen(addScreenRequest);
		return ResponseWrapper.success();
	}
}
