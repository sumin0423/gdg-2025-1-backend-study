package com.example.todo_api;

import com.example.todo_api.todo.Todo;
import com.example.todo_api.user.User;
import com.example.todo_api.category.Category;
import com.example.todo_api.todo.TodoRepository;
import com.example.todo_api.user.UserRepository;
import com.example.todo_api.category.CategoryRepository;
import lombok.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EntityScan(basePackages = "com.example.todo_api")
public class TodoApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoApiApplication.class, args);
	}

	@RestController
	@RequestMapping("/api/todos")
	@RequiredArgsConstructor
	public static class TodoController {
		private final TodoService todoService;

		@PostMapping
		public ResponseEntity<String> createTodo(@RequestBody TodoRequestDto dto) {
			try {
				todoService.createTodo(dto);
				return new ResponseEntity<>("할 일이 성공적으로 생성되었습니다.", HttpStatus.CREATED);
			} catch (IllegalArgumentException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
	}

	@Getter
	@NoArgsConstructor
	public static class TodoRequestDto {
		private String content;
		private Boolean isCompleted;
		private String date;
		private Long userId;
		private Long categoryId;
	}

	@Service
	@RequiredArgsConstructor
	public static class TodoService {

		private final TodoRepository todoRepository;
		private final UserRepository userRepository;
		private final CategoryRepository categoryRepository;

		public void createTodo(TodoRequestDto dto) {
			System.out.println("➡️ 유저 ID: " + dto.getUserId());
			System.out.println("➡️ 카테고리 ID: " + dto.getCategoryId());

			User user = userRepository.findById(dto.getUserId())
					.orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

			Category category = categoryRepository.findById(dto.getCategoryId())
					.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다."));

			Todo todo = new Todo(dto.getContent(), dto.getIsCompleted(), user, category);
			todo.setDate(dto.getDate());

			todoRepository.save(todo);
		}
	}
}