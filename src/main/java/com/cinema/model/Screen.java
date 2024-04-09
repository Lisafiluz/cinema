package com.cinema.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "screens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "screen_id", nullable = false)
	private int screenId;

	@OneToOne
	@JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
	private Movie movie;
	
	@OneToOne
	@JoinColumn(name = "hall_id", referencedColumnName = "hall_id")
	private Hall hall;
	
//	todo: fix here
	@OneToMany(mappedBy = "seatId", cascade = CascadeType.ALL)
	Set<Seat> seats;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp")
	private Date date;
	
}
