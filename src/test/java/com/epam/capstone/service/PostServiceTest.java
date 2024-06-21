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
        //given
        Post post1 = new Post();
        Post post2 = new Post();

        //when
        when(postRepository.findAll()).thenReturn(Arrays.asList(post1, post2));
        List<Post> posts = postServiceImpl.findAll();

        //then
        verify(postRepository, times(1)).findAll();
        assertEquals(2, posts.size());
    }


    @Test
    void testFindAllByAuthor_Id() {
        //given
        Post post1 = new Post();
        Post post2 = new Post();

        //when
        when(postRepository.findAllByAuthorId(1)).thenReturn(Arrays.asList(post1, post2));
        List<Post> posts = postServiceImpl.findAllByAuthor_Id(1);

        //then
        verify(postRepository, times(1)).findAllByAuthorId(1);
        assertEquals(2, posts.size());
    }

    @Test
    void shouldFindById() {
        //given
        Post post = new Post();
        post.setText("Test title");

        //when
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        Optional<Post> foundPost = postServiceImpl.findById(1);

        //then
        verify(postRepository, times(1)).findById(1L);
        assertTrue(foundPost.isPresent());
        assertEquals("Test title", foundPost.get().getText());
    }
}
