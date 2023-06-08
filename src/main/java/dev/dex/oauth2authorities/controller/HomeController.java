package dev.dex.oauth2authorities.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/client")
    public String client() {
        return "client";
    }

    @GetMapping("/my-login")
    public String login() {
        return "login";
    }
}
