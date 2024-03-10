package com.cinema.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinema.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
