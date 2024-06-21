package com.epam.capstone.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.epam.capstone.model.User;
import com.epam.capstone.repository.UserRepository;
import com.epam.capstone.security.UserDto;
import com.epam.capstone.service.imp.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldFindByUsername() {
        //given
        User user = new User();
        user.setUsername("user1");

        //when
        when(userRepository.findByUsername("user1")).thenReturn(Optional.of(user));
        User foundUser = userService.findByUsername("user1");

        //then
        verify(userRepository, times(1)).findByUsername("user1");
        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getUsername());
    }
}
