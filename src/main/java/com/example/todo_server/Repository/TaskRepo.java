package com.example.todo_server.Repository;

import com.example.todo_server.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long>{
    Optional<Task> findByTask(String task);

}
