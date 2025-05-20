package com.example.todo_api.todo;

import com.example.todo_api.category.Category;
import com.example.todo_api.user.User;
import com.example.todo_api.category.CategoryRepository;
import com.example.todo_api.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
        Todo todo = new Todo("할 일 작성 연습", false, user, category);
        todo.setDate("2025-05-20");
        todoRepository.save(todo);

        // ✅ 저장 검증
        assertThat(todo.getId()).isNotNull();
    }

    @AfterAll
    static void holdApp() throws InterruptedException {
        System.out.println("✅ 테스트 완료. 앱 종료 방지를 위해 대기 중입니다.");
        while (true) {
            Thread.sleep(1000);
        }
    }
}
