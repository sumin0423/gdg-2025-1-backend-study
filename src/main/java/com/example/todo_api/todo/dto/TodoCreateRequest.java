package com.example.todo_api.todo;

public class TodoCreateRequest {
    private String content;
    private Long userId;

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }
}