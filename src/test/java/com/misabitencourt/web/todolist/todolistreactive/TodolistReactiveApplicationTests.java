package com.misabitencourt.web.todolist.todolistreactive;

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
		return new Todo(todoTitle, new Date(), null, null);
	}

	@Test
	void shouldSaveTodo() throws ValidationError {
		final Todo todo = createBasicTodo();
		StepVerifier.create(
				todoService.create(todo)
		).assertNext(todoSaved -> {
			assertThat(todoSaved.getText()).isEqualTo(todo.getText());
			assertThat(todoSaved.getDeletedAt()).isNull();
		})
		.expectComplete()
		.verify();
	}

	@Test
	void shouldRetrieveTodo() throws ValidationError {
		/*StepVerifier.create(
				todoService.retrieve().collectList().filterWhen(todo -> {
					return Mono.just(true);
				})
		).expectComplete()
		 .verify();*/
	}

	@Test
	void shouldDeleteTodo() throws ValidationError {
//		StringBuilder todoTitle = new StringBuilder("Pay the tax at ");
//		todoTitle.append((new Date()).getTime());
//		Todo todo = new Todo(todoTitle.toString(), new Date(), null, null);
//		todoService.create(todo);
//		List<Todo> todos = todoService.retrieve();
//		assertThat(todos).isNotNull();
//		assertThat(todos.size()).isGreaterThan(0);
//		Todo first = todos.get(0);
//		assertThat(first.getText()).isEqualTo(todoTitle.toString());
//		todoService.delete(first);
//		todos = todoService.retrieve();
//		for (Todo todoItem : todos) {
//			assertThat(todoItem.getId()).isNotEqualTo(first.getId());
//		}
	}

	@Test
	void shouldMarkAsDone() throws ValidationError {
//		final String todoTitle = "Pay the tax";
//		Todo todo = new Todo(todoTitle, new Date(), null, null);
//		todo = todoService.create(todo);
//		todoService.maskAsDone(todo);
//		List<Todo> todoList = todoService.retrieve();
//		for (Todo todoItem : todoList) {
//			if (todoItem.getId() != null) {
//				if (todoItem.getId().equals(todo.getId())) {
//					assertThat(todo.getDoneAt()).isNotNull();
//				}
//			}
//		}
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
