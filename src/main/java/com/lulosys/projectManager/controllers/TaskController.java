package com.lulosys.projectManager.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.lulosys.projectManager.ModelResponses.CreateTaskModel;
import com.lulosys.projectManager.ModelResponses.CreateUserModel;
import com.lulosys.projectManager.ModelResponses.UpdateTaskModel;
import com.lulosys.projectManager.entitys.TaskEntity;
import com.lulosys.projectManager.entitys.UserEntity;
import com.lulosys.projectManager.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    UserController userController;

    @GetMapping()
    public ArrayList<TaskEntity> index() {
        return taskService.indexService();
    }

    @GetMapping(path = "/{id}")
    public Optional<TaskEntity> get(@PathVariable("id") Long id) {
        return this.taskService.getService(id);
    }

    @PostMapping()
    public TaskEntity post(@RequestBody CreateTaskModel task) {
        TaskEntity taskEntity = this.taskService.postService(task.getTask());
        if (task.getEmployee_id() != 0) {
            UserEntity userAssign = userController.get(task.getEmployee_id()).get();

            userAssign.setTaskId(taskEntity.getId());

            CreateUserModel userSetProject = new CreateUserModel();
            userSetProject.setUser(userAssign);
            userController.post(userSetProject);

        }
        return taskEntity;
    }

    @PostMapping(path = "/editTask")
    public TaskEntity update(@RequestBody UpdateTaskModel task) {
        try {

            if (task.getCompleted()) {

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        TaskEntity taskEntity = this.taskService.postService(task.getCreateTaskModel().getTask());

        try {
            UserEntity old = userController.get(task.getId_userAsigned()).get();
            System.out.println("\n\n\n\n EMployee : " + old.getName());
            old.setTaskId(null);
            CreateUserModel updteUser = new CreateUserModel();
            updteUser.setDate(String.valueOf(old.getHiring_date().toString()));
            updteUser.setUser(old);

            userController.post(updteUser);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (task.getCreateTaskModel().getEmployee_id() != 0) {
            UserEntity userAssign = userController.get(task.getCreateTaskModel().getEmployee_id()).get();

            userAssign.setTaskId(taskEntity.getId());

            CreateUserModel userSetProject = new CreateUserModel();
            userSetProject.setUser(userAssign);
            userController.post(userSetProject);

        }
        return taskEntity;
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        try {

            UserEntity user = userController.getUserByTaskId(id);

            System.out.println("\n\n\n\n EMployee : " + user.getName());
            user.setTaskId(null);
            CreateUserModel updteUser = new CreateUserModel();
            updteUser.setDate(String.valueOf(user.getHiring_date().toString()));
            updteUser.setUser(user);

            userController.post(updteUser);
        } catch (Exception e) {
            // TODO: handle exception
        }

        boolean ok = this.taskService.deleteService(id);
        if (ok) {
            return "Delete task " + id;
        } else {
            return "Failed delete task" + id;
        }
    }

    @GetMapping(path = "/projectId={projectId}")
    ArrayList<TaskEntity> getTasksByProjectId(@PathVariable("projectId") long projectId) {
        try {
            return taskService.getByProjectIdService(projectId);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
}
