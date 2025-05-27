package com.example.todo_api.todo;

import com.example.todo_api.todo.dto.TodoCreateRequest;
import com.example.todo_api.todo.dto.TodoUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Void> createTodo(@RequestBody TodoCreateRequest request) {
        Long id = todoService.createTodo(request);
        return ResponseEntity.created(URI.create("/todo/" + id)).build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Todo>> getTodos(@RequestParam Long userId) {
        return ResponseEntity.ok(todoService.findTodos(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequest request) {
        todoService.updateTodo(id, request.getContent());
        return ResponseEntity.noContent().build();
    }
}