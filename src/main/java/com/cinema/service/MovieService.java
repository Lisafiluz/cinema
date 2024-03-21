package com.cinema.service;

import java.util.List;

import com.cinema.dto.MovieDto;

public interface MovieService {
	MovieDto getMovie(Integer id);
	
	List<MovieDto> getPopularMovies();
	
	List<MovieDto> getMovieByGenre(String genre);
}
