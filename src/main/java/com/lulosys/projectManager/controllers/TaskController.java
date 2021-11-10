package com.lulosys.projectManager.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.lulosys.projectManager.entitys.TaskEntity;
import com.lulosys.projectManager.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping()
    public ArrayList<TaskEntity> index() {
        return taskService.indexService();
    }

    @GetMapping(path = "/{id}")
    public Optional<TaskEntity> get(@PathVariable("id") Long id) {
        return this.taskService.getService(id);
    }

    @PostMapping()
    public TaskEntity post(@RequestBody TaskEntity task) {
        return this.taskService.postService(task);
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        boolean ok = this.taskService.deleteService(id);
        if (ok) {
            return "Delete task " + id;
        } else {
            return "Failed delete task" + id;
        }
    }

    ArrayList<TaskEntity> getTasksByProjectId(long id) {
        return taskService.getByProjectIdService(id);
    }
}
