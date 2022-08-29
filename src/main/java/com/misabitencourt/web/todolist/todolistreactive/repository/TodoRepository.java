package com.misabitencourt.web.todolist.todolistreactive.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.misabitencourt.web.todolist.todolistreactive.entity.Todo;

import reactor.core.publisher.Flux;

@EntityScan(basePackages = {"com.misabitencourt.entity.*"})
public interface TodoRepository extends ReactiveCrudRepository<Todo, Long> {
}
