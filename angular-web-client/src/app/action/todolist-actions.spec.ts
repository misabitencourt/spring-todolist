import { Todo } from './../models/todo';
import { initialTodoListState } from './../state/todolist-state';
import { todolistActions, TODOLIST_ACTION } from './todolist-actions';

const createTodoList = (): Array<Todo> => [
  {id: 'abc', text: 'Buy some groceries', createdAt: new Date()},
  {id: 'abc-123', text: 'Do the homework', createdAt: new Date()},
  {id: 'abc-1234', text: 'Clean the room', createdAt: new Date()}
];

describe('Todolist actions', () => {

  it('Should Load', () => {
    const state = initialTodoListState();
    expect(state.editingTodo).toBeFalsy();
    expect(state.loaded).toBeFalsy();
    expect(state.todoList.length).toBe(0);
    const load = todolistActions.get(TODOLIST_ACTION.LOAD_TODOLIST);
    if (!load) {
      throw { msg: 'Action is not defined' };
    }
    const todoListLoaded = load(state, createTodoList());
    expect(todoListLoaded.editingTodo).toBeFalsy();
    expect(todoListLoaded.loaded).toBeTrue();
    expect(todoListLoaded.todoList.length).toBe(3);
  });


  it('Should Edit', () => {
    const state = initialTodoListState();
    expect(state.editingTodo).toBeFalsy();
    expect(state.loaded).toBeFalsy();
    expect(state.todoList.length).toBe(0);
    const load = todolistActions.get(TODOLIST_ACTION.LOAD_TODOLIST);
    if (!load) {
      throw { msg: 'Action is not defined' };
    }
    const todoList = createTodoList();
    const todoListLoaded = load(state, todoList);
    const edit = todolistActions.get(TODOLIST_ACTION.EDIT_TODO);
    if (!edit) {
      throw { msg: 'Action is not defined' };
    }
    const editingTodo = todoList[1];
    const editingTodoList = edit(todoListLoaded, editingTodo);
    expect(editingTodoList.loaded).toBeTrue();
    expect(editingTodoList.todoList.length).toBe(3);
    expect(editingTodoList.editingTodo).not.toBeNull();
    expect(editingTodoList.editingTodo?.id).toEqual(editingTodo.id);
    expect(editingTodoList.editingTodo?.createdAt).toEqual(editingTodo.createdAt);
    expect(editingTodoList.editingTodo?.text).toEqual(editingTodo.text);
    expect(editingTodoList.editingTodo?.doneAt).toEqual(editingTodo.doneAt);
  });


  it('Should Delete', () => {

  });
});
