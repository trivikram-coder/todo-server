package com.example.todo_server.Service;

import com.example.todo_server.Models.Task;
import com.example.todo_server.Repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    public void addTask(Task task){
        taskRepo.save(task);
    }
    public List<Task> getTasks(){
        return taskRepo.findAll();
    }
    public Task updateTask(String oldTask,String newTask){
        Task task=taskRepo.findByTask(oldTask).orElse(null);
        if(task==null){
            return null;
        }
        task.setTask(newTask);
        return taskRepo.save(task);
    }
    public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }

}
