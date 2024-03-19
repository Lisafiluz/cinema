package com.cinema.dto;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
	
	private int movieId;
	private String title;
	private String description;
	private Date releaseDate;
	private String genre;
	private String picUrl;
	private String trailerUrl;
	private int duration;
	private Boolean isPopular;
	private Integer review;
}
