package com.example.crmbackend.Service.Authentication;

import com.example.crmbackend.Model.UserG;
import com.example.crmbackend.Repository.UserGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserGRepository userGRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //find the user based on it's email
        UserG userG = userGRepository.findUserGByEmail(email);
        return new User(userG.getEmail(),userG.getPassword(), Collections.emptyList());
    }
}
