package com.example.todo_api.todo;

import com.example.todo_api.category.Category;
import com.example.todo_api.category.CategoryRepository;
import com.example.todo_api.user.User;
import com.example.todo_api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository; // ✅ 빠진 부분
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Todo createTodo(TodoRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("❗ 유저가 존재하지 않습니다."));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("❗ 카테고리가 존재하지 않습니다."));

        Todo todo = Todo.builder()
                .content(dto.getContent())
                .isCompleted(dto.isCompleted())
                .user(user)
                .category(category)
                .build();

        todo.setDate(dto.getDate());

        return todoRepository.save(todo); // ✅ 이제 오류 없음
    }
}

