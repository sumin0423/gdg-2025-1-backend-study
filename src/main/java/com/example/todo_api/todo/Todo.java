package com.example.todo_api.todo;

import com.example.todo_api.category.Category;
import com.example.todo_api.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel; // ✅ 추가해야 함
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(name = "todo_content", columnDefinition = "varchar(200)")
    private String content;

    @Column(name = "todo_is_check", columnDefinition = "boolean")
    private Boolean isCompleted;

    @Column(name = "todo_date", columnDefinition = "varchar(100)")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Todo(String content, Boolean isCompleted, User user, Category category) {
        this.content = content;
        this.isCompleted = isCompleted;
        this.user = user;
        this.category = category;
    }

    // ✅ 날짜는 생성자 외부에서 따로 설정
    public void setDate(String date) {
        this.date = date;
    }
}