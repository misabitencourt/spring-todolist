package com.misabitencourt.web.todolist.todolistreactive.repository;

import java.util.UUID;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.misabitencourt.web.todolist.todolistreactive.entity.Todo;

import reactor.core.publisher.Flux;

//@EntityScan(basePackages = {"com.misabitencourt.entity.*"})
public interface TodoRepository extends ReactiveCrudRepository<Todo, UUID> {

  @Query("select t.* from Todo t where t.deleted_at is null order by created_at desc")
  public Flux<Todo> retrieve();

}
