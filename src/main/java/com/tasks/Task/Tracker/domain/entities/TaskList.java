package com.tasks.Task.Tracker.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="task_lists")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_lists_id",updatable = false,nullable = false)
    private Long taskListId;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name = "created",nullable = false)
    private LocalDateTime created;

    @Column(name = "updated",nullable = false)
    private LocalDateTime updated;


    @OneToMany(mappedBy = "taskList",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Task> tasks=new ArrayList<>();
}
