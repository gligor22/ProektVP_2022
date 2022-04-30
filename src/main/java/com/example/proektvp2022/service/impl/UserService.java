package com.example.proektvp2022.service.impl;

import com.example.proektvp2022.model.Role;
import com.example.proektvp2022.model.User;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.model.exceptions.InvalidUserCredentialsException;
import com.example.proektvp2022.repository.JpaUserRepo;
import com.example.proektvp2022.service.UserServiceInterface;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    public final JpaUserRepo userRepo;
    public final PasswordEncoder passwordEncoder;

    public UserService(JpaUserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepo.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeat, String name, String surname, String adminCode) {
                if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        if(!password.equals(repeat))
        {
            throw new InvalidArgumentsException();
        }
        User user;
        if(adminCode.equals("1234")) {
             user = new User(username, passwordEncoder.encode(password), name, surname, Role.ROLE_ADMIN);
        }
        else
        {
            user = new User(username, passwordEncoder.encode(password), name, surname, Role.ROLE_USER);
        }
        userRepo.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(InvalidUserCredentialsException::new);
    }
}
