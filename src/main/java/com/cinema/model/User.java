package com.cinema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "user_id")
	private Integer id;
	
	private String username;
	
	private String password;
	
	@Column(name = "is_admin")
	private boolean isAdmin;
	
	public User(int id, String username, String password, boolean isAdmin) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public User() {
	
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}
}
