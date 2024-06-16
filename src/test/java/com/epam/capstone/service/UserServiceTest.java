package com.epam.capstone.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.epam.capstone.model.User;
import com.epam.capstone.repository.UserRepository;
import com.epam.capstone.security.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        UserDto user1 = new UserDto(1L, "user1", "user1@example.com");
        UserDto user2 = new UserDto(2L, "user2", "user2@example.com");

        when(userRepository.findAllUserDto()).thenReturn(Arrays.asList(user1, user2));

        List<UserDto> users = userService.findAll();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAllUserDto();
    }

    @Test
    void testFindById() {
        UserDto user = new UserDto(1L, "user1", "user1@example.com");

        when(userRepository.findByIdUserDto(1)).thenReturn(user);

        UserDto foundUser = userService.findById(1);

        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getUsername());
        verify(userRepository, times(1)).findByIdUserDto(1);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setUsername("user1");

        userService.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setUsername("user1");

        when(userRepository.findByUsername("user1")).thenReturn(user);

        User foundUser = userService.findByUsername("user1");

        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getUsername());
        verify(userRepository, times(1)).findByUsername("user1");
    }
}
