package com.tasks.Task.Tracker.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id",updatable = false,nullable = false)
    private Long taskId;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name="status",nullable = false)
    private TaskStatus taskStatus;

    @Column(name="priority",nullable = false)
    private TaskPriority taskPriority;

    @Column(name = "created",nullable = false)
    private LocalDateTime created;

    @Column(name = "updated",nullable = false)
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;
}
