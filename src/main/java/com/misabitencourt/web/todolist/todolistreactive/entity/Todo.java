package com.misabitencourt.web.todolist.todolistreactive.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;



@Entity
public class Todo extends AbstractPersistable<Long> {

    private static final long serialVersionUID = -2952735933715107252L;

    private String text;
	private Date createdAt;
    private Date doneAt;
    private Date deletedAt;

    public Todo() {}

    public Todo(String text, Date createdAt, Date doneAt, Date deletedAt) {
        this.text = text;
        this.createdAt = createdAt;
        this.doneAt = doneAt;
        this.deletedAt = deletedAt;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDoneAt() {
        return doneAt;
    }

    public void setDoneAt(Date doneAt) {
        this.doneAt = doneAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
