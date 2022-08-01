package com.misabitencourt.web.todolist.todolistreactive.service;

import com.misabitencourt.web.todolist.todolistreactive.entity.Todo;
import com.misabitencourt.web.todolist.todolistreactive.exception.ValidationError;
import com.misabitencourt.web.todolist.todolistreactive.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TodoService implements CRUDService<Todo> {
    @Autowired
    private TodoRepository repository;

    public void validate(Todo todo) throws ValidationError {
        if (todo.getText() == null || todo.getText().trim().isEmpty()) {
            throw new ValidationError(ValidationError.ERROR_REQUIRED, "text");
        }
        if (todo.getCreatedAt() == null) {
            throw new ValidationError(ValidationError.ERROR_REQUIRED, "createdAt");
        }
    }

    @Override
    public Todo create(Todo todo) throws ValidationError {
        this.validate(todo);
        return this.repository.save(todo);
    }

    @Override
    public List<Todo> retrieve() {
        return this.repository.retrieve();
    }

    @Override
    public Todo update(Todo todo) {
        return this.repository.save(todo);
    }

    @Override
    public void delete(Todo todo) {
        todo.setDeletedAt(new Date());
        this.update(todo);
    }

    public void maskAsDone(Todo todo) {
        todo.setDoneAt(new Date());
        this.update(todo);
    }

}
