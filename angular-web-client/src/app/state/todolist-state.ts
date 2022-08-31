import { Todo } from './../models/todo';

export type TodolistState = {
  loaded: boolean;
  loading: boolean;
  todoList: Array<Todo>;
  editingTodo: Todo | null;
};

export const initialTodoListState = (): TodolistState => ({
  loaded: false,
  loading: false,
  todoList: [],
  editingTodo: null
});
