package com.example.crmbackend.Service;

import com.example.crmbackend.DTO.AdminDTO;
import com.example.crmbackend.DTO.CustomerDTO;
import com.example.crmbackend.DTO.UserDTO;
import com.example.crmbackend.Repository.UserGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crmbackend.IService.UserIService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserIService {

    @Autowired
    private UserGRepository userGRepository;
    @Override
    public UserDTO findUserGByEmail(String email) {
        return UserDTO.fromEntity(userGRepository.findUserGByEmail(email));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userGRepository.findAll().stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }
}
