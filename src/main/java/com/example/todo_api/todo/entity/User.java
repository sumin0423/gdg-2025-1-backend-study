package com.example.todo_api.todo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // 🔧 user는 예약어 → 테이블 이름 변경
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String profileImageUrl;
    private String introduction;

    public User(String name, String profileImageUrl, String introduction) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
    }
}