package com.avyukt.full.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avyukt.full.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
