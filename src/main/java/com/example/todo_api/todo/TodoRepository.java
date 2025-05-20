package com.example.todo_api.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // 필요에 따라 커스텀 쿼리 메서드를 여기에 추가할 수 있어요
}

