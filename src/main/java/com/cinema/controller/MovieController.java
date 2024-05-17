package com.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.ResponseWrapper;
import com.cinema.dto.MovieDto;
import com.cinema.exception.ServiceException;
import com.cinema.request.UpdateMovieRequest;
import com.cinema.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	private final MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseWrapper<MovieDto> getMovie(@PathVariable Integer id) {
		MovieDto movie = movieService.getMovie(id);
		return ResponseWrapper.success(movie);
	}
	
	@GetMapping(value = "/popular", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseWrapper<MovieDto> getPopularMovies() {
		List<MovieDto> popularMovies = movieService.getPopularMovies();
		return ResponseWrapper.success(popularMovies);
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseWrapper<Void> updateMovie(@RequestBody UpdateMovieRequest updateMovieRequest) throws ServiceException, IllegalAccessException {
		movieService.updateMovie(updateMovieRequest);
		return ResponseWrapper.success();
	}
}
