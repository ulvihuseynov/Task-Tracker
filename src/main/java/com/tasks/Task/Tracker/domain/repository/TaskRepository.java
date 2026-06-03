package com.tasks.Task.Tracker.domain.repository;

import com.tasks.Task.Tracker.domain.entities.Task;
import com.tasks.Task.Tracker.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByTaskListTaskListId(Long taskListId);
    Optional<Task> findByTaskListTaskListIdAndTaskId(Long taskListId,Long taskId);
}
