package com.epam.capstone.service;

import com.epam.capstone.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();

    List<Post> findAllByAuthor_Id(Integer id);

//    Post save(Post post);

    Optional<Post> findById(Integer id);
}
