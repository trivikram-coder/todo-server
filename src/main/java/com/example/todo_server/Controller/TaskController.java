package com.example.todo_server.Controller;

import com.example.todo_server.Models.Task;
import com.example.todo_server.Service.TaskService;
import com.example.todo_server.dto.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody Task task){
        if(task==null || task.getTask().isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("message","Task is required"));
        }
        taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message","Task added successfully"));
    }
    @GetMapping("/read")
    public ResponseEntity<?> readTasks(){
        List<Task> tasks=taskService.getTasks();
        if(tasks==null || tasks.isEmpty()){
            return ResponseEntity.badRequest().body(Map.of("message","No tasks exists"));
        }
        return ResponseEntity.ok().body(Map.of("data",tasks));
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateTask(@RequestParam("oldTask") String oldTask,@RequestBody Dto dto){
        String newTask=dto.getTask();

        Task updated= taskService.updateTask(oldTask,newTask);
        if(updated==null){
            return ResponseEntity.badRequest().body(Map.of("message","Task not updated"));
        }
        return ResponseEntity.ok().body(Map.of("Task updated",updated));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok().body(Map.of("message","Task deleted"));
    }
}
