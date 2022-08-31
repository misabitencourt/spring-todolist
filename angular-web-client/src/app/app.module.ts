import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { TodolistComponent } from './compose/todolist/todolist.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TodolistComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
