package com.misabitencourt.web.todolist.todolistreactive.entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo implements Persistable<UUID> {

    private static final long serialVersionUID = -2952735933715107252L;

    @Transient
    private boolean isNew;

    @Id
    @Column("id")
    private UUID id;

    private String text;
	  private LocalTime createdAt;
    private LocalTime doneAt;
    private LocalTime deletedAt;

}
