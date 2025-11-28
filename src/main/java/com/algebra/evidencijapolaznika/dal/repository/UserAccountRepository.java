package com.algebra.evidencijapolaznika.dal.repository;


import com.algebra.evidencijapolaznika.dal.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    Optional<UserAccount> findByUsername(String username);
}
