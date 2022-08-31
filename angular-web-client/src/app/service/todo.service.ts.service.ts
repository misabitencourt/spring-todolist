import { Todo } from './../models/todo';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TodoServiceTsService {

  constructor(
    private http: HttpClient
  ) { }

  loadTodolist() {
    return new Promise((resolve, reject) => {
      this.http.get(`${environment.apiUrl}/todo/`).subscribe({
        next(todoList) {
          resolve(todoList);
        },
        error(err) {
          reject(err);
        }
      })
    });
  }

  destroyTodo(todo: Todo) {
    return new Promise((resolve, reject) => {
      this.http.delete(`${environment.apiUrl}/todo/${todo.id}/`).subscribe({
        next() {
          resolve(null);
        },
        error(err) {
          reject(err);
        }
      })
    });
  }

  save(todo: Todo) {
    return new Promise((resolve, reject) => {
      this.http.post(`${environment.apiUrl}/todo/`, JSON.stringify(todo), {
        headers: {'Content-Type': 'application/json'}
      }).subscribe({
        next() {
          resolve(null);
        },
        error(err) {
          reject(err);
        }
      })
    });
  }
}
