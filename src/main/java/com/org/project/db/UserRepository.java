package com.org.project.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { 
} 