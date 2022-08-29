package com.misabitencourt.web.todolist.todolistreactive.entity;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;


@Data
@Getter
@Setter
@NoArgsConstructor
public class Todo implements Persistable<Long> {

    private static final long serialVersionUID = -2952735933715107252L;

    @Transient
    private boolean isNew;

    @Id
    private Long id;
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
