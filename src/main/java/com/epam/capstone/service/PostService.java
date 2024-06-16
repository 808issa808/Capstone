package com.epam.capstone.service;

import com.epam.capstone.model.Post;
import com.epam.capstone.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> findAll()
    {
        return postRepository.findAll();
    }

    public List<Post> findAllByAuthor_Id(Integer id)
    {
        return postRepository.findAllByAuthor_Id(id);
    }

    public Post save(Post post)
    {
        return postRepository.save(post);
    }

    public Optional<Post> findById(Integer id) {
        return postRepository.findById(id.longValue());
    }
}
