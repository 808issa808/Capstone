package com.epam.capstone.service.imp;

import com.epam.capstone.model.User;
import com.epam.capstone.repository.UserRepository;
import com.epam.capstone.security.UserDto;
import com.epam.capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto findById(Integer id)
    {
        User user=userRepository.findById(id.longValue()).get();
        return new UserDto(user.getId(),user.getUsername(),user.getEmail());
    }

    public void saveUserWithNewEmail(String email) {
        User user = userRepository.findByUsername("user1").get();
        user.setEmail(email);
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public void register( String username,  String email,  String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }
    public boolean login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()  && bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
            return true;  // Login successful
        }
        return false;  // Login failed
    }

}
