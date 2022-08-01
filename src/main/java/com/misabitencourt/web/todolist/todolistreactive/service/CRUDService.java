package com.misabitencourt.web.todolist.todolistreactive.service;

import com.misabitencourt.web.todolist.todolistreactive.exception.ValidationError;

import java.util.List;

public interface CRUDService <T> {
    T create(T entity) throws ValidationError;
    List<T> retrieve();

    @SuppressWarnings("UnusedReturnValue")
    T update(T entity) throws ValidationError;
    void delete(T entity);
}
