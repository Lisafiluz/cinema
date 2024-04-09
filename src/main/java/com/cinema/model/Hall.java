package com.cinema.model;

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
@Table(name = "halls")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hall {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hall_id", nullable = false)
	private int hallId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "rows_count", nullable = false)
	private Integer rows;
	
	@Column(name = "cols_count", nullable = false)
	private Integer cols;
	
}
