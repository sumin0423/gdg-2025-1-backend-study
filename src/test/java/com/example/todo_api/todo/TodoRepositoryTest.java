package com.example.todo_api.todo;

import com.example.todo_api.todo.entity.Todo;
import com.example.todo_api.todo.entity.User;
import com.example.todo_api.todo.entity.Category;
import com.example.todo_api.todo.repository.TodoRepository;
import com.example.todo_api.todo.repository.UserRepository;
import com.example.todo_api.todo.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    @Rollback(false)
    void todoCreateTest() {
        // ✅ 임시 유저 저장
        User user = new User("홍길동", null, null);
        userRepository.save(user);

        // ✅ 임시 카테고리 저장
        Category category = new Category("공부", "#FF0000", false, user);
        categoryRepository.save(category);

        // ✅ 할 일 저장
        Todo todo = new Todo("할 일 작성 연습", false, "2025-05-20", user, category);
        todoRepository.save(todo);

        // ✅ 저장 검증
        assertThat(todo.getId()).isNotNull();
    }
}