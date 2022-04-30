package com.example.proektvp2022.service;


import com.example.proektvp2022.model.Role;
import com.example.proektvp2022.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {
    User login(String username, String password);

    User register(String username, String password, String repeat, String name, String surname, String adminCode);
}
