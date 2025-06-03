package com.example.todo_api.todo.dto;

import com.example.todo_api.todo.entity.Todo;
import lombok.Getter;

@Getter
public class TodoResponseDto {
    private Long id;
    private String content;
    private boolean completed;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.content = todo.getContent();
        this.completed = todo.getIsCompleted(); // ✅ 올바른 getter 사용
    }
}