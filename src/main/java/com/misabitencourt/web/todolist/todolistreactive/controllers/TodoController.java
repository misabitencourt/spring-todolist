package com.misabitencourt.web.todolist.todolistreactive.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
