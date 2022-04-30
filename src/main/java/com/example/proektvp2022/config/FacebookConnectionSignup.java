package com.example.proektvp2022.config;

import com.example.proektvp2022.model.User;
import com.example.proektvp2022.repository.JpaUserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    private final JpaUserRepo userRepository;

    public FacebookConnectionSignup(JpaUserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword(randomAlphabetic(8));
        userRepository.save(user);
        return user.getUsername();
    }
}