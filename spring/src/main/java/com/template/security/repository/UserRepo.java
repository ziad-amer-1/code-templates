package com.template.security.repository;

import com.template.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo  extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
