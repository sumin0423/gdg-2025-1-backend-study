package com.example.todo_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.todo_api")
public class TodoApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoApiApplication.class, args);
	}
}