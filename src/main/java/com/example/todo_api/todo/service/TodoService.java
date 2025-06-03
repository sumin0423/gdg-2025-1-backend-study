package com.example.todo_api.todo.service;

import com.example.todo_api.common.message.ErrorMessage;
import com.example.todo_api.todo.dto.TodoCreateRequest;
import com.example.todo_api.todo.dto.TodoResponseDto;
import com.example.todo_api.todo.entity.Category;
import com.example.todo_api.todo.entity.Todo;
import com.example.todo_api.todo.entity.User;
import com.example.todo_api.todo.repository.CategoryRepository;
import com.example.todo_api.todo.repository.TodoRepository;
import com.example.todo_api.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TodoResponseDto createTodo(TodoCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.USER_NOT_FOUND));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.CATEGORY_NOT_FOUND));

        Todo todo = new Todo(
                request.getContent(),
                request.getIsCompleted(),
                request.getDate(),
                user,
                category
        );

        todoRepository.save(todo);

        return new TodoResponseDto(todo);
    }

    public List<TodoResponseDto> findTodos(Long userId) {
        return todoRepository.findByUserId(userId).stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.TODO_NOT_FOUND));

        System.out.println("삭제 시도 중인 Todo: " + todo.getContent());
        todoRepository.delete(todo);
    }
}