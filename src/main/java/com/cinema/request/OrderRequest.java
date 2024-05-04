package com.cinema.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
	private Integer screenId;
	private List<SeatRequest> seats;
}
