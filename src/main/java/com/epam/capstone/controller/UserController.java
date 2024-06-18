package com.epam.capstone.controller;

import com.epam.capstone.model.User;
import com.epam.capstone.service.imp.PostServiceImpl;
import com.epam.capstone.service.imp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.slf4j.helpers.Reporter.error;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    PostServiceImpl postServiceImpl;

//    @GetMapping("")
//    public String findAll(Model model) {
//        model.addAttribute("usersDto", userService.findAll());
//        return "user";
//    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Integer id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("posts", postServiceImpl.findAllByAuthor_Id(id));

        return "user";
    }

    @GetMapping("/own/{id}")
    public String findByIdOwn(Model model, @PathVariable Integer id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("posts", postServiceImpl.findAllByAuthor_Id(id));

        return "ownProfile";
    }

    @PostMapping("/update-email")
    @ResponseBody
    public ResponseEntity updateEmail(@RequestParam String email, Principal principal) {
        try {
            userService.saveUserWithNewEmail(email);
            return ResponseEntity.ok("Email updated successfully");
        } catch (Exception e) {
            error("Error updating email", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating email");
        }
    }

}
