package com.algebra.evidencijapolaznika.bll.interfaces;

import com.algebra.evidencijapolaznika.dal.entity.UserAccount;
import com.algebra.evidencijapolaznika.dto.Request.UserAccountCreateRequestDTO;

import java.util.Optional;

public interface UserAccountService {
    boolean create(UserAccountCreateRequestDTO dto);
    Optional<UserAccount> findByUsername(String username);
}
