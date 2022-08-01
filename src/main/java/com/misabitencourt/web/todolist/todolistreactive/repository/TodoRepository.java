package com.misabitencourt.web.todolist.todolistreactive.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.misabitencourt.web.todolist.todolistreactive.entity.Todo;

import java.util.List;

@EntityScan(basePackages = {"com.misabitencourt.entity.*"})
public interface TodoRepository extends CrudRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t WHERE t.deletedAt IS NULL ORDER BY t.id DESC")
    List<Todo> retrieve();

}
