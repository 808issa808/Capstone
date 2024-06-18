package com.epam.capstone.controller;

import com.epam.capstone.model.Post;
import com.epam.capstone.service.imp.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostServiceImpl postServiceImpl;


    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("posts", postServiceImpl.findAll());
        return "postList";
    }

    @GetMapping("/author/{id}")
    public String findAllByAuthorId(Model model, @PathVariable Integer id) {
        model.addAttribute("posts", postServiceImpl.findAllByAuthor_Id(id));
        return "postList";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Integer id) {
        model.addAttribute("post", postServiceImpl.findById(id).get());
        model.addAttribute("comments", postServiceImpl.findById(id).get().getComments());
        return "post";
    }


    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<String> createPost(@RequestParam String text, Principal principal) {
        Post post = postServiceImpl.save(text, principal);

        if (post != null) {
            return ResponseEntity.ok("Post created successfully");
        }
        return ResponseEntity.status(400).body("Failed to create post");
    }

    @PostMapping("{postId}/comment")
    @ResponseBody
    public ResponseEntity<String> createComment(@PathVariable Integer postId, @RequestParam String text, Principal principal) {

        Post post = postServiceImpl.saveCommentUnderPost(postId, text, principal);
        if (post != null) {
            return ResponseEntity.ok("Comment created successfully");
        }
        return ResponseEntity.status(400).body("Failed to create comment");
    }


}
