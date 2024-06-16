package com.epam.capstone.controller;

import com.epam.capstone.model.Comment;
import com.epam.capstone.model.Post;
import com.epam.capstone.model.User;
import com.epam.capstone.service.CommentService;
import com.epam.capstone.service.PostService;
import com.epam.capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "postList";
    }

    @GetMapping("/author/{id}")
    public String findAllByAuthorId(Model model, @PathVariable Integer id) {
        model.addAttribute("posts", postService.findAllByAuthor_Id(id));
        return "postList";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Integer id) {
        model.addAttribute("post", postService.findById(id).get());
        model.addAttribute("comments", postService.findById(id).get().getComments());
        return "post";
    }


    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<String> createPost(@RequestParam String text, Principal principal) {
//        User user = userService.findByUsername(principal.getName());
        User user = userService.findByUsername("user");
        if (user != null) {
            Post post = new Post();
            post.setText(text);
            post.setAuthor(user);
            post.setCreatedAt(LocalDateTime.now());
            postService.save(post);
            return ResponseEntity.ok("Post created successfully");
        }
        return ResponseEntity.status(400).body("Failed to create post");
    }

    @PostMapping("{postId}/comment")
    @ResponseBody
    public ResponseEntity<String> createComment(@PathVariable Integer postId, @RequestParam String text, Principal principal) {
//        User user = userService.findByUsername(principal.getName());
        User user = userService.findByUsername("user");
        if (user != null) {
            Comment comment = new Comment();
            comment.setText(text);
            comment.setAuthor(user);
            comment.setCreatedAt(LocalDateTime.now());
            comment.setPost(postService.findById(postId).get());
            commentService.save(comment);
            return ResponseEntity.ok("Comment created successfully");
        }
        return ResponseEntity.status(400).body("Failed to create comment");
    }


}
