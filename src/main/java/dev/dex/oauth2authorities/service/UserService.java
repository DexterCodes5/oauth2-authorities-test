package dev.dex.oauth2authorities.service;

import dev.dex.oauth2authorities.entity.*;
import dev.dex.oauth2authorities.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {
    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void processOAuthPostLogin(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            return;
        }

        jdbcTemplate.update("INSERT INTO users (username, enabled, provider) VALUES (?,?,?)",
                username, true, "GOOGLE");
        jdbcTemplate.update("INSERT INTO authorities VALUES (?,?)", username, "ROLE_CLIENT");
    }
}
