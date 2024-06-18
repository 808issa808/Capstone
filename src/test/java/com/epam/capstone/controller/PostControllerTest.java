package com.epam.capstone.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epam.capstone.model.Comment;
import com.epam.capstone.model.Post;
import com.epam.capstone.model.User;
import com.epam.capstone.service.imp.CommentServiceImpl;
import com.epam.capstone.service.imp.PostServiceImpl;
import com.epam.capstone.service.imp.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostServiceImpl postServiceImpl;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private CommentServiceImpl commentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void shouldCreatePost() throws Exception {
        User user = new User();
        user.setUsername("user");

        when(userService.findByUsername("user")).thenReturn(user);

        mockMvc.perform(post("/posts/create")
                        .param("text", "Test post")
                        .principal(() -> "user")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Post created successfully"));

        verify(userService, times(1)).findByUsername("user");
//        verify(postServiceImpl, times(1)).save(any(Post.class));
    }

    @Test
    void shouldCreateComment() throws Exception {
        User user = new User();
        user.setUsername("user");
        Post post = new Post();
        post.setId(1L);

        when(userService.findByUsername("user")).thenReturn(user);
        when(postServiceImpl.findById(1)).thenReturn(Optional.of(post));

        mockMvc.perform(post("/posts/1/comment")
                        .param("text", "Test comment")
                        .principal(() -> "user")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Comment created successfully"));

        verify(userService, times(1)).findByUsername("user");
        verify(postServiceImpl, times(1)).findById(1);
        verify(commentServiceImpl, times(1)).save(any(Comment.class));
    }
}
