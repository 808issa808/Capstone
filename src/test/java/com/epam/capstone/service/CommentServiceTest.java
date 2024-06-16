package com.epam.capstone.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.epam.capstone.model.Comment;
import com.epam.capstone.repository.CommentRepository;
import com.epam.capstone.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveComment() {
        // given
        Comment comment = new Comment();
        comment.setText("Test comment");
        when(commentRepository.save(comment)).thenReturn(comment);

        // when
        Comment savedComment = commentService.save(comment);

        // then
        verify(commentRepository, times(1)).save(comment);
        assertEquals("Test comment", savedComment.getText());
    }
}
