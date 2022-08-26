package com.example.crmbackend.IService;

import com.example.crmbackend.DTO.CustomerDTO;
import com.example.crmbackend.DTO.UserDTO;

import java.util.List;

public interface UserIService {

    public UserDTO findUserGByEmail(String email);

    public List<UserDTO> getAllUsers();

}
