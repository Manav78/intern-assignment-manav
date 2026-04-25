package com.project.assignment.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post post;

    private String content;
    private int depthLevel;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private User userAuthor;

    @ManyToOne Bot botAuthor;
}
