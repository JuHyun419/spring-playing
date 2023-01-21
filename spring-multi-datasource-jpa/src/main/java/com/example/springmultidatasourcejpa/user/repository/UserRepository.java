package com.example.springmultidatasourcejpa.user.repository;

import com.example.springmultidatasourcejpa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
