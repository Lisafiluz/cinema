package com.cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.dto.MovieDto;
import com.cinema.mapper.MovieMapper;
import com.cinema.model.Movie;
import com.cinema.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	private final MovieRepository movieRepository;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	@Override
	public MovieDto getMovie(Integer id) {
		Optional<Movie> movie = movieRepository.findById(id);
		return MovieMapper.INSTANCE.movieToMovieDto(movie.orElse(null));
	}
	
	@Override
	public List<MovieDto> getPopularMovies() {
		List<Movie> popularMovies = movieRepository.findByIsPopular(true);
		return popularMovies.stream().map(MovieMapper.INSTANCE::movieToMovieDto).collect(Collectors.toList());
	}
	
	@Override
	public List<MovieDto> getMovieByGenre(String genre) {
		List<Movie> movies = movieRepository.findByGenre(genre);
		return movies.stream().map(MovieMapper.INSTANCE::movieToMovieDto).collect(Collectors.toList());
	}
}
