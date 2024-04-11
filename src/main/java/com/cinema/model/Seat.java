package com.cinema.model;

import com.cinema.enums.SeatStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seat_id", nullable = false)
	private int seatId;
	
	@JoinColumn(name = "screen_id")
	private int screenId;
	
	@Column(name = "row", nullable = false)
	private int row;
	
	@Column(name = "seat_number", nullable = false)
	private int seatNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private SeatStatus status;
	
//	@ManyToOne
//	@JoinColumn(name = "screen_id")
//	private Screen screen;
	
	
}
