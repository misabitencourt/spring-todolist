package com.misabitencourt.web.todolist.todolistreactive.controllers;

import com.misabitencourt.web.todolist.todolistreactive.entity.Todo;
import com.misabitencourt.web.todolist.todolistreactive.exception.ValidationError;
import com.misabitencourt.web.todolist.todolistreactive.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/heart-beat/")
    public String index() {
        return "Alive!";
    }

    @PostMapping(value = "/todo/")
    public Todo create(@RequestBody Todo todo) throws ValidationError {
        return todoService.create(todo);
    }
}
