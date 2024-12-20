package com.app.repository;

import com.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find user by username
    Optional<User> findByUsername(String username);

    // Custom method to delete user by username
    void deleteByUsername(String username);
}
