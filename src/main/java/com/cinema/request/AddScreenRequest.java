package com.cinema.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddScreenRequest {
	private Integer movieId;
	private Integer hallId;
	private Date date;
}
