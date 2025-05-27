package com.example.todo_api.todo;

import com.example.todo_api.todo.dto.TodoCreateRequest;
import com.example.todo_api.todo.entity.Todo;
import com.example.todo_api.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    // ✅ 할 일 생성
    @Transactional
    public Long createTodo(TodoCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        List<Todo> existingTodos = todoRepository.findAllByUserId(user.getId());
        if (existingTodos.size() >= 10) {
            throw new IllegalStateException("할 일은 최대 10개까지 등록할 수 있습니다.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElse(null); // 카테고리는 선택 사항

        Todo todo = new Todo(request.getContent(), user, category);
        todoRepository.save(todo);
        return todo.getId();
    }

    // ✅ 할 일 목록 조회
    public List<Todo> findTodos(Long userId) {
        return todoRepository.findAllByUserId(userId);
    }

    // ✅ 할 일 삭제
    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    // ✅ 할 일 수정
    @Transactional
    public void updateTodo(Long id, String content) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 존재하지 않습니다."));
        todo.setContent(content);
    }
}