package com.example.demos.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demos.role.Role;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer>
{

    Optional<Token> findByToken( String token);
    void deleteByUser(User user);

    void deleteByUserId(Integer userId);
}
