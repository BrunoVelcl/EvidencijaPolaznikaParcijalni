package com.algebra.evidencijapolaznika.bll.implementation;

import com.algebra.evidencijapolaznika.bll.interfaces.UserAccountService;
import com.algebra.evidencijapolaznika.bll.pojo.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Primary
@Component
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UserAccountService userAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var rv = this.userAccountService.findByUsername(username);
        if(rv.isEmpty()) throw new UsernameNotFoundException("Username " + username + " not found");
        return new CustomUserDetails(rv.get());
    }

}
