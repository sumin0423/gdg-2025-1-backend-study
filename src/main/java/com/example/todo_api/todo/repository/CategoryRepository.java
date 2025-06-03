package com.example.todo_api.todo.repository;

import com.example.todo_api.todo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}