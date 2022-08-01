package com.misabitencourt.web.todolist.todolistreactive.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo extends AbstractPersistable<Long> {

    private static final long serialVersionUID = -2952735933715107252L;
    private String text;
	private Date createdAt;
    private Date doneAt;
    private Date deletedAt;

    public Todo(String text, Date createdAt, Date doneAt, Date deletedAt) {
        this.text = text;
        this.createdAt = createdAt;
        this.doneAt = doneAt;
        this.deletedAt = deletedAt;
    }

}
