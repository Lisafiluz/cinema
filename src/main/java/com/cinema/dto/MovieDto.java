package com.cinema.dto;
import java.util.Date;

public class MovieDto {
	
	private int movieId;
	private String title;
	private String description;
	private Date releaseDate;
	private String genre;
	private String picUrl;
	private String trailerUrl;
	private Boolean isPopular;
	private Integer review;
	
	
	public MovieDto() {
	}
	
	public MovieDto(int movieId, String title, String description, Date releaseDate, String genre, String picUrl, String trailerUrl, Boolean isPopular, Integer review) {
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
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
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
	
	public Boolean getIsPopular() {
		return isPopular;
	}
	
	public void setIsPopular(Boolean isPopular) {
		this.isPopular = isPopular;
	}
	
	public Integer getReview() {
		return review;
	}
	
	public void setReview(Integer review) {
		this.review = review;
	}
}
