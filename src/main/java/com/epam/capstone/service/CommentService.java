package com.epam.capstone.service;

import com.epam.capstone.model.Comment;
import com.epam.capstone.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment save(Comment comment)
    {
        return commentRepository.save(comment);
    }


}
