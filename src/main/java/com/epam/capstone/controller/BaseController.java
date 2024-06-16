package com.epam.capstone.controller;

import com.epam.capstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    @ResponseBody
    public String base() {
        return userRepository.findAll().toString();
    }
}
