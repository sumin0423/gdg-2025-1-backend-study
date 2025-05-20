package com.example.todo_api.todo;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String content;
    private boolean isCompleted;
    private String date;
    private Long userId;
    private Long categoryId;
}
