package com.scl.devnest.validation.repository;

import com.scl.devnest.validation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByEmail(String email);
}
