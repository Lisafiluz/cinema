package com.cinema.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
	@Id
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
	
	@Column(name = "is_popular")
	private Boolean isPopular;
	
	@Column(name = "review")
	private Integer review;
	
	public Movie() {
	}
	
	public Movie(int movieId, String title, String description, Date releaseDate, String genre, String picUrl, String trailerUrl, Boolean isPopular, Integer review) {
		this.movieId = movieId;
		this.title = title;
		this.description = description;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.picUrl = picUrl;
		this.trailerUrl = trailerUrl;
		this.isPopular = isPopular;
		this.review = review;
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int id) {
		this.movieId = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getPicUrl() {
		return picUrl;
	}
	
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	public String getTrailerUrl() {
		return trailerUrl;
	}
	
	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}
	
	public Boolean getPopular() {
		return isPopular;
	}
	
	public void setPopular(Boolean popular) {
		isPopular = popular;
	}
	
	public Integer getReview() {
		return review;
	}
	
	public void setReview(Integer review) {
		this.review = review;
	}
}
