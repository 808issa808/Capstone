package com.epam.capstone.controller;

import com.epam.capstone.model.User;
import com.epam.capstone.service.UserService;
import com.epam.capstone.service.imp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        boolean loginSuccessful = userService.login(username, password);
        if (loginSuccessful) {
            // Redirect to a success page or dashboard
            return "redirect:/posts";  // Example redirect to a dashboard endpoint
        } else {
            // Add error message and return to login page
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        userService.register(username,email,password);
        return "redirect:/login";
    }
}
