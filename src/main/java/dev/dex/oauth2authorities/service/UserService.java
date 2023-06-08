package dev.dex.oauth2authorities.service;

import dev.dex.oauth2authorities.entity.*;
import dev.dex.oauth2authorities.oauth2.*;
import dev.dex.oauth2authorities.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void processOAuthPostLogin(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            return;
        }

        User newUser = new User(username, true, Provider.GOOGLE);
        userRepository.save(newUser);
    }
}
