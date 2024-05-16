package com.cinema.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.dto.MovieDto;
import com.cinema.enums.ServiceResponseCode;
import com.cinema.exception.ServiceException;
import com.cinema.mapper.MovieMapper;
import com.cinema.model.Movie;
import com.cinema.repository.MovieRepository;
import com.cinema.request.UpdateMovieRequest;

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
	
	@Override
	public List<MovieDto> getAllMovies() {
		List<Movie> movie = movieRepository.findAll();
		return movie.stream().map(MovieMapper.INSTANCE::movieToMovieDto).collect(Collectors.toList());
	}
	
	@Override
	public void updateMovie(UpdateMovieRequest updateMovieRequest) throws ServiceException, IllegalAccessException {
		int movieId = updateMovieRequest.getMovieId();
		Optional<Movie> existMovieOptional = movieRepository.findById(movieId);
		
		if (existMovieOptional.isEmpty()) {
			throw new ServiceException(ServiceResponseCode.ERROR_BAD_REQUEST, "The movie has not found");
		}
		
		validateMovieContent(updateMovieRequest);
		//can validate here the correctness od the data
		
		Movie movieToUpdate = existMovieOptional.get();
		movieToUpdate.setTitle(updateMovieRequest.getTitle());
		movieToUpdate.setDescription(updateMovieRequest.getDescription());
		movieToUpdate.setReleaseDate(updateMovieRequest.getReleaseDate());
		movieToUpdate.setGenre(updateMovieRequest.getGenre());
		movieToUpdate.setPicUrl(updateMovieRequest.getPicUrl());
		movieToUpdate.setTrailerUrl(updateMovieRequest.getTrailerUrl());
		movieToUpdate.setDuration(updateMovieRequest.getDuration());
		movieToUpdate.setIsPopular(updateMovieRequest.getIsPopular());
		movieToUpdate.setReview(updateMovieRequest.getReview());
		
		movieRepository.save(movieToUpdate);
	}
	
	private void validateMovieContent(UpdateMovieRequest movie) throws ServiceException {
		if (!checkAllFieldsNotNull(movie)) {
			throw new ServiceException(ServiceResponseCode.ERROR_INVALID_INPUT, "Invalid movie content - fields can't be null");
		}
		
		if (movie.getDuration() <= 0) {
			throw new ServiceException(ServiceResponseCode.ERROR_INVALID_INPUT, "Invalid movie content - duration can't be non-positive");
		}
		
		if (movie.getReview() < 0 || movie.getReview() > 100) {
			throw new ServiceException(ServiceResponseCode.ERROR_INVALID_INPUT, "Invalid movie content - review score must be in range of 0 to 100");
		}
		
	}
	
	public static boolean checkAllFieldsNotNull(Object obj) throws ServiceException {
		if (obj == null) {
			return false;
		}
		
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.get(obj) == null || field.get(obj).equals("")) {
					return false;
				}
			} catch (IllegalAccessException e) {
				throw new ServiceException(ServiceResponseCode.ERROR_INVALID_INPUT, e.getMessage());
			}
		}
		return true;
	}
}
