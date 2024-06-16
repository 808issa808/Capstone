package com.epam.capstone.repository;

import com.epam.capstone.model.User;
import com.epam.capstone.security.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT new com.epam.capstone.security.UserDto(u.id, u.username,u.email) FROM User u")
    List<UserDto> findAllUserDto();

    @Query("SELECT new com.epam.capstone.security.UserDto(u.id, u.username,u.email) FROM User u where u.id=:id")
    UserDto findByIdUserDto(@Param("id") Integer id);

    User findByUsername(String username);


}
