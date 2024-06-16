package com.epam.capstone.service;

import com.epam.capstone.model.User;
import com.epam.capstone.repository.UserRepository;
import com.epam.capstone.security.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDto> findAll()
    {
        return userRepository.findAllUserDto();
    }

    public UserDto findById(Integer id)
    {
        return userRepository.findByIdUserDto(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
