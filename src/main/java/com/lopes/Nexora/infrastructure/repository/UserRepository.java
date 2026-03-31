package com.lopes.Nexora.infrastructure.repository;

import com.lopes.Nexora.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
