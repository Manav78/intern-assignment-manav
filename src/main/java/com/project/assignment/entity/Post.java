package com.project.assignment.entity;
import  jakarta.persistence.*;
import  lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userAuthor;

    @ManyToOne
    @JoinColumn(name = "bot_id")
    private Bot botAuthor;
}
