package com.example.todo_api.todo.repository;

import com.example.todo_api.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}