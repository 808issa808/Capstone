package com.epam.capstone.service.imp;

import com.epam.capstone.model.Comment;
import com.epam.capstone.repository.CommentRepository;
import com.epam.capstone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment save(Comment comment)
    {
        return commentRepository.save(comment);
    }


}
