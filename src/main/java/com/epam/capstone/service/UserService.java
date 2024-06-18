package com.epam.capstone.service;

import com.epam.capstone.model.User;
import com.epam.capstone.security.UserDto;

import java.util.List;

public interface UserService {

//    List<UserDto> findAll();

    UserDto findById(Integer id);

//    void save(User user);

    User findByUsername(String username);
}
