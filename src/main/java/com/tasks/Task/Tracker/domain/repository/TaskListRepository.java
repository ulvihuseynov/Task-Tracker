package com.tasks.Task.Tracker.domain.repository;

import com.tasks.Task.Tracker.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList,Long> {
}
