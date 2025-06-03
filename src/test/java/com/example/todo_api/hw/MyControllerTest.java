package com.example.todo_api.hw;

import com.example.todo_api.hw.controller.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MyControllerTest {

    @Autowired
    private MyController myController;

    @Test
    void testHelloController() {
        String result = myController.helloController();
        assertThat(result).isEqualTo("controller → service → repository");
    }
}
