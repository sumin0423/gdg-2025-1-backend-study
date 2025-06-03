package com.example.todo_api.hw.controller;

import com.example.todo_api.todo.entity.Category;
import com.example.todo_api.todo.entity.User;
import com.example.todo_api.todo.repository.CategoryRepository;
import com.example.todo_api.todo.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dev")
@RequiredArgsConstructor
@Tag(name = "Dev Init API", description = "개발 초기화를 위한 API")
public class DevInitController {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Operation(
            summary = "초기 유저/카테고리 생성",
            description = "초기 유저 이름과 상태 메시지를 파라미터로 받아 저장합니다."
    )
    @ApiResponse(responseCode = "200", description = "초기화 성공")
    @PostMapping("/init")
    public ResponseEntity<String> init(
            @Parameter(description = "초기 유저 이름", example = "철수")
            @RequestParam String name,

            @Parameter(description = "초기 상태 메시지", example = "자바 공부 중")
            @RequestParam String status
    ) {
        User user = new User(name, "https://example.com/img.png", status);
        userRepository.save(user);

        Category category = new Category("공부", "blue", false, user);
        categoryRepository.save(category);

        return ResponseEntity.ok("✅ 초기 유저와 카테고리 생성 완료");
    }
}