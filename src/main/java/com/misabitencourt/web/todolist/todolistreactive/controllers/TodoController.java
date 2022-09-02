package com.misabitencourt.web.todolist.todolistreactive.controllers;

import java.time.LocalTime;
import java.util.UUID;
import com.misabitencourt.web.todolist.todolistreactive.entity.Todo;
import com.misabitencourt.web.todolist.todolistreactive.exception.ValidationError;
import com.misabitencourt.web.todolist.todolistreactive.repository.TodoRepository;
import com.misabitencourt.web.todolist.todolistreactive.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @GetMapping("/heart-beat/")
    public Mono<String> index() {
        return Mono.just("Alive!");
    }

    @GetMapping(value = "/todo/")
    public Flux<Todo> retrieve() {
        return todoRepository.retrieve();
    }

    @PostMapping(value = "/todo/")
    public Mono<Todo> create(@RequestBody Todo todo) throws ValidationError {
        todo.setCreatedAt(LocalTime.now());
        return todoService.create(todo);
    }

    @PutMapping(value = "/todo/{id}/")
    public Mono<Todo> update(@RequestBody Todo todo) throws ValidationError {
        todo.setNew(false);
        return todoRepository.save(todo).onErrorReturn(todo);
    }

    @DeleteMapping(value = "/todo/{id}/")
    public Mono<Boolean> delete(@PathVariable UUID id) {
        return todoRepository.findById(id).flatMap(todo -> {
            todo.setDeletedAt(LocalTime.now());
            return todoRepository.save(todo)
                .then(Mono.just(Boolean.TRUE)).onErrorReturn(true);
        });
    }
}
