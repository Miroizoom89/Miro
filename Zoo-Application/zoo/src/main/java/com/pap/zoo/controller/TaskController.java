package com.pap.zoo.controller;

import com.pap.zoo.entity.Task;
import com.pap.zoo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public Pair<Boolean, String> addTask(@RequestBody Task task) { return taskService.saveTask(task); }

    @GetMapping("/list")
    public List<Task> findAllTasks() { return taskService.getTasks(); }

    @GetMapping("/taskById/{id}")
    public Task findTaskById(@PathVariable int id) { return taskService.getTaskById(id); }

    @GetMapping("/taskByLogin/{login}")
    public List<Task> findTaskByLogin(@PathVariable String login) { return taskService.getTaskByLogin(login); }

    @PutMapping("/update")///check
    public Pair<Boolean, String> updateTask(@RequestBody Task task) { return taskService.updateTask(task); }

    @DeleteMapping("/delete/{id}")
    public Pair<Boolean, String> deleteTask(@PathVariable int id) { return taskService.deleteTask(id); }
}
