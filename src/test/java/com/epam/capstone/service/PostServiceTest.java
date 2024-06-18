package com.epam.capstone.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.epam.capstone.model.Post;
import com.epam.capstone.repository.PostRepository;
import com.epam.capstone.service.imp.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PostServiceTest {

    @InjectMocks
    private PostServiceImpl postServiceImpl;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Post post1 = new Post();
        Post post2 = new Post();

        when(postRepository.findAll()).thenReturn(Arrays.asList(post1, post2));

        List<Post> posts = postServiceImpl.findAll();

        assertEquals(2, posts.size());
        verify(postRepository, times(1)).findAll();
    }

//    @Test
//    void testSavePost() {
//        Post post = new Post();
//        post.setText("Test title");
//
//        when(postRepository.save(post)).thenReturn(post);
//
//        Post savedPost = postServiceImpl.save(post);
//
//        assertEquals("Test title", savedPost.getText());
//        verify(postRepository, times(1)).save(post);
//    }

    @Test
    void testFindAllByAuthor_Id() {
        Post post1 = new Post();
        Post post2 = new Post();

        when(postRepository.findAllByAuthorId(1)).thenReturn(Arrays.asList(post1, post2));

        List<Post> posts = postServiceImpl.findAllByAuthor_Id(1);

        assertEquals(2, posts.size());
        verify(postRepository, times(1)).findAllByAuthorId(1);
    }

    @Test
    void testFindById() {
        Post post = new Post();
        post.setText("Test title");

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        Optional<Post> foundPost = postServiceImpl.findById(1);

        assertTrue(foundPost.isPresent());
        assertEquals("Test title", foundPost.get().getText());
        verify(postRepository, times(1)).findById(1L);
    }
}
