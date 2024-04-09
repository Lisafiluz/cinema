package com.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinema.model.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Integer> {

}
