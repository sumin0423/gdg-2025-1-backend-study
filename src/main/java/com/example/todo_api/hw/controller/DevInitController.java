package com.example.todo_api.hw.controller;

import com.example.todo_api.category.Category;
import com.example.todo_api.category.CategoryRepository;
import com.example.todo_api.user.User;
import com.example.todo_api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dev")
@RequiredArgsConstructor
public class DevInitController {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @PostMapping("/init")
    public String init() {
        User user = new User("철수", "https://example.com/img.png", "자바 공부 중");
        userRepository.save(user);

        Category category = new Category("공부", "blue", false, user);
        categoryRepository.save(category);

        return "✅ 초기 유저와 카테고리 생성 완료";
    }
}

