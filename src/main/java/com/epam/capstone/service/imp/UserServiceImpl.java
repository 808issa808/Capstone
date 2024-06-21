package com.epam.capstone.service.imp;

import com.epam.capstone.exception.UsernameAlreadyTakenException;
import com.epam.capstone.model.Role;
import com.epam.capstone.model.User;
import com.epam.capstone.repository.RoleRepository;
import com.epam.capstone.repository.UserRepository;
import com.epam.capstone.security.UserDto;
import com.epam.capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto findById(Integer id)
    {
        User user=userRepository.findById(id.longValue()).get();
        return new UserDto(user.getId(),user.getUsername(),user.getEmail());
    }

    public void saveUserWithNewEmail(String email, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        user.setEmail(email);
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


    public void register( String username,  String email,  String password) {
        if (existsByUsername(username)) {
            throw new UsernameAlreadyTakenException("Username already taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findById(2L).orElseThrow(() -> new RuntimeException("Default role not found"));
        roles.add(userRole);
        user.setRoles(roles);
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


