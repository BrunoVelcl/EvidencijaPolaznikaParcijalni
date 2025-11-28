package com.algebra.evidencijapolaznika.bll.implementation;

import com.algebra.evidencijapolaznika.bll.interfaces.UserAccountService;
import com.algebra.evidencijapolaznika.component.PwEncoder;
import com.algebra.evidencijapolaznika.dal.entity.UserAccount;
import com.algebra.evidencijapolaznika.dal.repository.UserAccountRepository;
import com.algebra.evidencijapolaznika.dto.Request.UserAccountCreateRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PwEncoder pwEncoder;

    @Override
    public boolean create(UserAccountCreateRequestDTO dto) {
        try {
            this.userAccountRepository.save(new UserAccount(dto.getUsername(), this.pwEncoder.getEncoder().encode(dto.getPassword())));
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return this.userAccountRepository.findByUsername(username);
    }
}
