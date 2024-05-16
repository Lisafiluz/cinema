package com.cinema.service;

import java.util.List;

import com.cinema.dto.MovieDto;
import com.cinema.exception.ServiceException;
import com.cinema.request.UpdateMovieRequest;

public interface MovieService {
	MovieDto getMovie(Integer id);
	
	List<MovieDto> getPopularMovies();
	
	List<MovieDto> getMovieByGenre(String genre);
	
	List<MovieDto> getAllMovies();
	
	void updateMovie(UpdateMovieRequest updateMovieRequest) throws ServiceException, IllegalAccessException;
}
