package com.cinema.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.dto.ScreenDto;
import com.cinema.enums.ServiceResponseCode;
import com.cinema.exception.ServiceException;
import com.cinema.mapper.ScreenMapper;
import com.cinema.model.Hall;
import com.cinema.model.Movie;
import com.cinema.model.Screen;
import com.cinema.repository.HallRepository;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.ScreenRepository;
import com.cinema.request.AddScreenRequest;

@Service
public class ScreenServiceImpl implements ScreenService {
	
	private final ScreenRepository screenRepository;
	private final MovieService movieService;
	private final MovieRepository movieRepository;
	private final HallRepository hallRepository;
	
	@Autowired
	public ScreenServiceImpl(ScreenRepository screenRepository, MovieService movieService, MovieRepository movieRepository, HallRepository hallRepository) {
		this.screenRepository = screenRepository;
		this.movieService = movieService;
		this.movieRepository = movieRepository;
		this.hallRepository = hallRepository;
	}
	
	@Override
	public ScreenDto getScreen(Integer screenId) throws ServiceException {
		Optional<Screen> screen = screenRepository.findById(screenId);
		if (screen.isEmpty()) {
			throw new ServiceException(ServiceResponseCode.ERROR_NOT_FOUND, "The Screen was not found");
		}
		return ScreenMapper.INSTANCE.screenToScreenDto(screen.get());
	}

	@Override
	public List<ScreenDto> getScreensByMovieId(Integer movieId) throws ServiceException {
		if (movieService.getMovie(movieId) == null) {
			throw new ServiceException(ServiceResponseCode.ERROR_NOT_FOUND, "The movie was not found");
		}
		List<Screen> screens = screenRepository.findScreensByMovie_MovieId(movieId);
		return screens.stream()
				.map(ScreenMapper.INSTANCE::screenToScreenDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public Map<Integer, ScreenDto> getScreenIdToScreenDto(Set<Integer> screenIds) {
		List<Screen> screens = screenRepository.findAllById(screenIds);
		return screens.stream().map(ScreenMapper.INSTANCE::screenToScreenDto).collect(Collectors.toMap((ScreenDto::getScreenId), b -> b));
	}
	
	@Override
	public List<ScreenDto> getAllScreens() {
		List<Screen> screens = screenRepository.findAll();
		return screens.stream().map(ScreenMapper.INSTANCE::screenToScreenDto).collect(Collectors.toList());
	}
	
	@Override
	public void createScreen(AddScreenRequest addScreenRequest) throws ServiceException {
		Screen screen = new Screen();
		
		
		Movie movie = movieRepository.findById(addScreenRequest.getMovieId()).orElse(null);
		Hall hall = hallRepository.findById(addScreenRequest.getHallId()).orElse(null);
		
		if ( movie == null || hall == null) {
			throw new ServiceException(ServiceResponseCode.ERROR_INVALID_INPUT, "Invalid input for hall id or movie id");
		}
		
		if (System.currentTimeMillis() > addScreenRequest.getDate().getTime()) {
			throw new ServiceException(ServiceResponseCode.ERROR_INVALID_INPUT, "Invalid input for screen date - can't create screen in past");
		}
		
		screen.setMovie(movie);
		screen.setHall(hall);
		screen.setDate(addScreenRequest.getDate());
		
		screenRepository.save(screen);
	}
}
