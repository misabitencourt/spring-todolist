package com.misabitencourt.web.todolist.todolistreactive;

import java.util.Date;
import java.util.List;

import com.misabitencourt.web.todolist.todolistreactive.exception.ValidationError;
import com.misabitencourt.web.todolist.todolistreactive.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.misabitencourt.web.todolist.todolistreactive.entity.Todo;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.reactive.function.BodyInserters;

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
	void shouldValidateAndCreateTodos() throws Exception {
		Todo todo = new Todo(null, null, null, null);
		try {
			todoService.create(todo);
			throw new Exception("Todo does not validating.");
		} catch (ValidationError e) {
			assertThat(e.getField()).isEqualTo("text");
			assertThat(e.getError()).isEqualTo(ValidationError.ERROR_REQUIRED);
		}
		try {
			todo.setText("Test");
			todoService.create(todo);
			throw new Exception("Todo does not validating.");
		} catch (ValidationError e) {
			assertThat(e.getField()).isEqualTo("createdAt");
			assertThat(e.getError()).isEqualTo(ValidationError.ERROR_REQUIRED);
		}
		todo.setCreatedAt(new Date());
		todoService.create(todo);
	}

	@Test
	void shouldRetrieveTodos() throws ValidationError {
		Todo todo = createBasicTodo();
		todoService.create(todo);
		List<Todo> todoList = todoService.retrieve();
		assertThat(todoList).isNotNull();
		assertThat(todoList.size()).isGreaterThan(0);
		assertThat(todoList.get(todoList.size()-1).getText()).isEqualTo(todo.getText());
	}

	@Test
	void shouldDeleteTodo() throws ValidationError {
		StringBuilder todoTitle = new StringBuilder("Pay the tax at ");
		todoTitle.append((new Date()).getTime());
		Todo todo = new Todo(todoTitle.toString(), new Date(), null, null);
		todoService.create(todo);
		List<Todo> todos = todoService.retrieve();
		assertThat(todos).isNotNull();
		assertThat(todos.size()).isGreaterThan(0);
		Todo first = todos.get(0);
		assertThat(first.getText()).isEqualTo(todoTitle.toString());
		todoService.delete(first);
		todos = todoService.retrieve();
		for (Todo todoItem : todos) {
			assertThat(todoItem.getId()).isNotEqualTo(first.getId());
		}
	}

	@Test
	void shouldMarkAsDone() throws ValidationError {
		final String todoTitle = "Pay the tax";
		Todo todo = new Todo(todoTitle, new Date(), null, null);
		todo = todoService.create(todo);
		todoService.maskAsDone(todo);
		List<Todo> todoList = todoService.retrieve();
		for (Todo todoItem : todoList) {
			if (todoItem.getId() != null) {
				if (todoItem.getId().equals(todo.getId())) {
					assertThat(todo.getDoneAt()).isNotNull();
				}
			}
		}
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
