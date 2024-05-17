package com.cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinema.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	List<Movie> findByGenre(String genre);
	
	List<Movie> findByIsPopular(boolean isPopular);
	
	// Use query example
	@Query("select '*' FROM Movie WHERE genre = 'action'")
	List<Movie> getAllActionMovies();
	
}
