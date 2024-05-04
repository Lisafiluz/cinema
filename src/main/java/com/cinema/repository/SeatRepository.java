package com.cinema.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinema.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
	Set<Seat> getAllByScreenId(Integer screenId);
	
}
