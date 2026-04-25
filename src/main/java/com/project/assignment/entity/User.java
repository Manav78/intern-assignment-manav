package com.project.assignment.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private boolean isPremium;
}
