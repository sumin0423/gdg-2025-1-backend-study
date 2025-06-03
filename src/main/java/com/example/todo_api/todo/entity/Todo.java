package com.example.todo_api.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter  // ⬅️ 이 어노테이션이 꼭 필요합니다!
@NoArgsConstructor
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private Boolean isCompleted; // Boolean 타입이라면 getIsCompleted()가 생성됨
    private String date;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    public Todo(String content, Boolean isCompleted, String date, User user, Category category) {
        this.content = content;
        this.isCompleted = isCompleted;
        this.date = date;
        this.user = user;
        this.category = category;
    }
}