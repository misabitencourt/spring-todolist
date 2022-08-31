import { TodolistState } from './../state/todolist-state';

export enum TODOLIST_ACTION {
  LOAD_TODOLIST,
  CREATE_TODO,
  UPDATE_TODO,
  DELETE_TODO,
  EDIT_TODO,
  MARK_TODO_AS_DONE
};

export const todolistActions: Map<TODOLIST_ACTION, (state: TodolistState, payload: any) => TodolistState> = new Map<TODOLIST_ACTION, () => TodolistState>();

todolistActions.set(TODOLIST_ACTION.LOAD_TODOLIST, (state: TodolistState, payload: any) => ({
  ...state,
  loaded: true,
  loading: false,
  todoList: [...(payload || [])]
}));


todolistActions.set(TODOLIST_ACTION.DELETE_TODO, (state: TodolistState, payload: any) => ({
  ...state,
  loaded: true,
  loading: false,
  todoList: state.todoList.filter(todo => todo.id !== payload?.id)
}));


todolistActions.set(TODOLIST_ACTION.EDIT_TODO, (state: TodolistState, payload: any) => ({
  ...state,
  editingTodo: payload ? {...payload} : null
}));
