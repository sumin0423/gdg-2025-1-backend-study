package com.example.todo_api.todo.controller;

import com.example.todo_api.todo.dto.TodoCreateRequest;
import com.example.todo_api.todo.dto.TodoResponseDto;
import com.example.todo_api.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Tag(name = "Todo API", description = "할 일 관리 API")
public class TodoController {

    private final TodoService todoService;

    @Operation(summary = "할 일 생성", description = "새로운 할 일을 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "할 일 생성 성공"),
            @ApiResponse(responseCode = "400", description = "유효성 검증 실패")
    })
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(
            @Valid @RequestBody TodoCreateRequest request) {
        TodoResponseDto response = todoService.createTodo(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "할 일 목록 조회", description = "특정 사용자 ID의 할 일 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/list")
    public ResponseEntity<List<TodoResponseDto>> getTodos(
            @Parameter(name = "userId", description = "조회할 사용자 ID", required = true)
            @RequestParam Long userId) {
        return ResponseEntity.ok(todoService.findTodos(userId));
    }

    @Operation(summary = "할 일 삭제", description = "할 일 ID를 기준으로 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 Todo ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @Parameter(name = "id", description = "삭제할 할 일의 ID", required = true)
            @PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}