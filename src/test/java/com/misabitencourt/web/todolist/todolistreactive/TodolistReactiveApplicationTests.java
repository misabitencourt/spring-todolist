package com.misabitencourt.web.todolist.todolistreactive;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.misabitencourt.web.todolist.todolistreactive.exception.ValidationError;
import com.misabitencourt.web.todolist.todolistreactive.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.misabitencourt.web.todolist.todolistreactive.entity.Todo;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
class TodolistReactiveApplicationTests {

	@Autowired
	TodoService todoService;

	@Autowired
	private WebTestClient webClient;

	@Test
	void contextLoads() {}

	private Todo createBasicTodo() {
		final String todoTitle = "Pay the tax";
		return new Todo(false, null, todoTitle, LocalTime.now(), null, null);
	}

	@Test
	void shouldSaveTodo() throws ValidationError {
		final Todo todo = createBasicTodo();
		StepVerifier.create(
				todoService.create(todo).map(todoSaved -> {
					assertThat(todoSaved.getText()).isEqualTo(todo.getText());
					assertThat(todoSaved.getDeletedAt()).isNull();
					return todoService.retrieve().collectList().map(todoList -> {
						assertThat(todoList.isEmpty()).isFalse();
						return todoList;
					});
				})
		).assertNext(todoList -> todoList.thenReturn(true))
		.expectComplete()
		.verify();
	}

	@Test
	void shouldDeleteTodo() throws ValidationError {
		final Todo todo = createBasicTodo();
		StepVerifier.create(
			todoService.create(todo).map(todoSaved -> {
				return todoService.delete(todo).map(deleted -> {
					assertThat(deleted).isTrue();
					return Mono.just(true);
				});
			})
		).assertNext(deletedTodo -> deletedTodo.thenReturn(true))
		 .expectComplete()
		 .verify();
	}

	@Test
	void shouldMarkAsDone() throws ValidationError {
		final Todo todo = createBasicTodo();
		StepVerifier.create(
				todoService.create(todo).map(todoSaved -> {
					return todoService.maskAsDone(todo).map(updated -> {
						assertThat(updated).isTrue();
						return Mono.just(true);
					});
				})
		).assertNext(updatedTodo -> updatedTodo.thenReturn(true))
		 .expectComplete()
		 .verify();
	}
	@Test
	void shouldPing() {
		webClient.get()
						.uri("/heart-beat/")
						.exchange()
						.expectStatus()
						.isOk()
						.expectBody(String.class)
						.isEqualTo("Alive!");
	}

	@Test
	void shouldPostTodo() {
		Todo todo = createBasicTodo();
		webClient.post()
				.uri("/todo/")
				.accept(MediaType.APPLICATION_JSON)
				.header("Content-Type", "application/json")
				.body(BodyInserters.fromValue(todo))
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(Todo.class);
	}

}
