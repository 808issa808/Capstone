package com.epam.capstone.controller;

import com.epam.capstone.model.User;
import com.epam.capstone.service.PostService;
import com.epam.capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @GetMapping("")
    public String findAll(Model model) {
        model.addAttribute("usersDto", userService.findAll());
        return "user";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Integer id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("posts", postService.findAllByAuthor_Id(id));

        return "user";
    }

    @GetMapping("/own/{id}")
    public String findByIdOwn(Model model, @PathVariable Integer id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("posts", postService.findAllByAuthor_Id(id));

        return "ownProfile";
    }

    @PostMapping("/update-email")
    @ResponseBody
    public ResponseEntity updateEmail(@RequestParam String email) {

//        if (user != null && user.getId().equals(userId)) {
//            userService.updateEmail(user.getId(), email);
//            return "Email updated successfully";
//        }
//        return "Error updating email";
        try {
            User user = userService.findByUsername("user");
            user.setEmail(email);
            userService.save(user);
            return ResponseEntity.ok("Email updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating email");
        }
    }

}
