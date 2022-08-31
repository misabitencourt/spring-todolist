package com.misabitencourt.web.todolist.todolistreactive.service;

import com.misabitencourt.web.todolist.todolistreactive.entity.Todo;
import com.misabitencourt.web.todolist.todolistreactive.exception.ValidationError;
import com.misabitencourt.web.todolist.todolistreactive.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoService implements CRUDService<Todo> {
    @Autowired
    private TodoRepository repository;

    public Mono<Boolean> validate(Todo todo) {
        if (todo.getText() == null || todo.getText().trim().isEmpty()) {
            return Mono.error(new ValidationError(ValidationError.ERROR_REQUIRED, "createdAt"));
        }
        if (todo.getCreatedAt() == null) {
            return Mono.error(new ValidationError(ValidationError.ERROR_REQUIRED, "createdAt"));
        }
        return Mono.just(true);
    }

    @Override
    public Mono<Todo> create(Todo todo) throws ValidationError {
        return this.validate(todo).flatMap(valid -> {
            todo.setNew(true);
            todo.setId(UUID.randomUUID());
            return this.repository.save(todo);
        });
    }

    @Override
    public Flux<Todo> retrieve() {
        return this.repository.retrieve();
    }

    @Override
    public Mono<Todo> update(Todo todo) {
        return this.validate(todo).flatMap(valid -> this.repository.save(todo));
    }

    @Override
    public Mono<Boolean> delete(Todo todo) {
        return repository.findById(todo.getId()).map(todoLoaded -> {
            todoLoaded.setDeletedAt(LocalTime.now());
            todoLoaded.setNew(false);
            return this.repository.save(todoLoaded).thenReturn(Boolean.TRUE);
        }).thenReturn(Boolean.TRUE);
    }

    public Mono<Boolean> maskAsDone(Todo todo) {
        todo.setDoneAt(LocalTime.now());
        return this.update(todo).thenReturn(Boolean.TRUE);
    }

}
