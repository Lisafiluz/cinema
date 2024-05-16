package com.cinema.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "screens",uniqueConstraints = {@UniqueConstraint(columnNames = "screen_id")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "screen_id", nullable = false)
	private Integer screenId;

	@OneToOne
	@JoinColumn(name = "movie_id", referencedColumnName = "movie_id")
	private Movie movie;
	
	@OneToOne
	@JoinColumn(name = "hall_id", referencedColumnName = "hall_id")
	private Hall hall;
	
	@OneToMany(mappedBy = "screenId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	Set<Seat> seats;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timestamp")
	private Date date;
	
}
