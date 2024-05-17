package com.cinema.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "movie_id", nullable = false)
	private int movieId;
	
	@Column(name = "title", nullable = false, length = 30)
	private String title;
	
	@Column(name = "description", nullable = false, length = 30)
	private String description;
	
	@Column(name = "release_date", nullable = false)
	private Date releaseDate;
	
	@Column(name = "genre", nullable = false, length = 100)
	private String genre;
	
	@Column(name = "pic_url", length = 2083)
	private String picUrl;
	
	@Column(name = "trailer_url", length = 2083)
	private String trailerUrl;
	
	@Column(name = "duration", nullable = false)
	private int duration;
	
	@Column(name = "is_popular")
	private Boolean isPopular;
	
	@Column(name = "review")
	private Integer review;
	
	@Override
	public String toString() {
		return "Movie{" +
					 "movieId=" + movieId +
					 ", title='" + title + '\'' +
					 ", description='" + description + '\'' +
					 ", releaseDate=" + releaseDate +
					 ", genre='" + genre + '\'' +
					 ", picUrl='" + picUrl + '\'' +
					 ", trailerUrl='" + trailerUrl + '\'' +
					 ", isPopular=" + isPopular +
					 ", review=" + review +
					 '}';
	}
}
