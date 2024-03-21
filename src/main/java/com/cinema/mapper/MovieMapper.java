package com.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cinema.dto.MovieDto;
import com.cinema.model.Movie;

@Mapper
public interface MovieMapper {
	MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);
	
	MovieDto movieToMovieDto(Movie movie);
	
	Movie movieDtoToMovie(MovieDto dto);
}
