import { TodolistState, initialTodoListState } from './../state/todolist-state';
import { TODOLIST_ACTION, todolistActions } from './todolist-actions';

export const todolistAction = (action: TODOLIST_ACTION, state: TodolistState, payload: any): TodolistState => {
  return todolistActions.get(action)?.(state, payload) || initialTodoListState();
};
