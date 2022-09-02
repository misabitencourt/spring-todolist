import { Todo } from './../../models/todo';
import { todolistAction } from './../../action/index';
import { TodoServiceTsService } from './../../service/todo.service.ts.service';
import { TODOLIST_ACTION } from './../../action/todolist-actions';
import { TodolistState, initialTodoListState } from './../../state/todolist-state';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-todolist',
  templateUrl: './todolist.component.html'
})
export class TodolistComponent implements OnInit {

  constructor(
    private todolistService: TodoServiceTsService
  ) { }

  @Input()
  public todolistState: TodolistState = initialTodoListState();

  ngOnInit(): void {
    (async () => {
      try {
        const todoList = await this.todolistService.loadTodolist();
        this.todolistState = todolistAction(TODOLIST_ACTION.LOAD_TODOLIST, this.todolistState, todoList);
      } catch (err) {
        console.error(err);
      }
    })();
  }

  destroy(todo: Todo) {
    (async () => {
      try {
        await this.todolistService.destroyTodo(todo);
        this.todolistState = todolistAction(TODOLIST_ACTION.DELETE_TODO, this.todolistState, todo);
      } catch (err) {
        console.error(err);
      }
    })();
  }

  edit(todo: Todo | null) {
    this.todolistState = todolistAction(TODOLIST_ACTION.EDIT_TODO, this.todolistState, todo);
  }

  typed($event: Event, todo: Todo) {
    todo.text = ($event.target as any).value;
  }

  create() {
    const emptyTodo: Todo = { text: '' };
    this.todolistState = todolistAction(TODOLIST_ACTION.EDIT_TODO, this.todolistState, emptyTodo);
  }

  save() {
    (async () => {
      try {
        if (this.todolistState.editingTodo) {
          if (this.todolistState.editingTodo.id) {
            await this.todolistService.update(this.todolistState.editingTodo);
          } else {
            await this.todolistService.save(this.todolistState.editingTodo);
          }
        }
        this.edit(null);
        this.ngOnInit();
      } catch (err) {
        console.error(err);
      }
    })();
  }

}
