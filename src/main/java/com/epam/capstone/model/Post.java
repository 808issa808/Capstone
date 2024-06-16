package com.epam.capstone.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text")
    private String text;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    @OneToMany(mappedBy = "post",
            fetch =FetchType.LAZY)
    @ToString.Exclude // Исключаем поле из метода toString для предотвращения рекурсии
    private List<Comment> comments;
}
