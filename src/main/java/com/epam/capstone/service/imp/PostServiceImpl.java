package com.epam.capstone.service.imp;

import com.epam.capstone.model.Comment;
import com.epam.capstone.model.Post;
import com.epam.capstone.model.User;
import com.epam.capstone.repository.PostRepository;
import com.epam.capstone.security.CustomUserDetails;
import com.epam.capstone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    CommentServiceImpl commentServiceImpl;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findAllByAuthor_Id(Integer id) {
        return postRepository.findAllByAuthorId(id);
    }

    public Post save(String text, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (user != null) {
            Post post = new Post();
            post.setText(text);
            post.setAuthor(user);
            post.setCreatedAt(LocalDateTime.now());
            return postRepository.save(post);
        }
        return null;
    }

    public Post saveCommentUnderPost(Integer postId, String text, Principal principal) {
        Optional<Post> post = postRepository.findById(postId.longValue());
        User user = userService.findByUsername(principal.getName());

        if (post.isPresent()) {
            Comment comment = new Comment();
            comment.setText(text);
            comment.setAuthor(user);
            comment.setCreatedAt(LocalDateTime.now());
            comment.setPost(post.get());
            commentServiceImpl.save(comment);
            return post.get();
        }

        return null;
    }



    public Optional<Post> findById(Integer id) {
        return postRepository.findById(id.longValue());
    }

    public boolean deletePost(Long postId, CustomUserDetails principal) {
        Optional<Post> postOpt = postRepository.findById(postId);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            if (principal.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("Admin")) ||
                    post.getAuthor().getUsername().equals(principal.getUsername())) {
                postRepository.deleteById(postId);
                return true;
            }
        }
        return false;
    }
}
