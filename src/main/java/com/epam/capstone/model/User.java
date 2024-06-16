package com.epam.capstone.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "author",
            fetch =FetchType.LAZY)
    @ToString.Exclude // Исключаем поле из метода toString для предотвращения рекурсии
    private List<Post> posts;
}
