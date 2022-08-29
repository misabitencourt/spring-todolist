package com.misabitencourt.web.todolist.todolistreactive.service;

import com.misabitencourt.web.todolist.todolistreactive.exception.ValidationError;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CRUDService <T> {
    Mono<T> create(T entity) throws ValidationError;
    Flux<T> retrieve();

    @SuppressWarnings("UnusedReturnValue")
    Mono<T> update(T entity) throws ValidationError;
    Mono<Boolean> delete(T entity);
}
