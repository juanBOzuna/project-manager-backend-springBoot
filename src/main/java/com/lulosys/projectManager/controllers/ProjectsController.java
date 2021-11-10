package com.lulosys.projectManager.controllers;

import java.util.*;

import com.lulosys.projectManager.ModelResponses.ProjectsResponseModel;
import com.lulosys.projectManager.entitys.ProjectEntity;
import com.lulosys.projectManager.entitys.TaskEntity;
import com.lulosys.projectManager.entitys.UserEntity;
import com.lulosys.projectManager.services.ProjectsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
    @Autowired
    ProjectsService projectsService;
    @Autowired
    TaskController tasksController;
    @Autowired
    UserController userController;

    @GetMapping()
    public ArrayList<ProjectsResponseModel> index() {
        ArrayList<ProjectsResponseModel> projectsRepsonseList = new ArrayList<ProjectsResponseModel>();
        ProjectsResponseModel projectsResponseModel;
        // ArrayList<ProjectsEntity> projectsEntities = projectsService.indexService();

        for (ProjectEntity project : projectsService.indexService()) {
            projectsResponseModel = new ProjectsResponseModel();
            projectsResponseModel.setProject(project);
            projectsResponseModel.setTasks(getTasks(project.getId()));
            projectsResponseModel.setPromotor(getPromotor(project.getPromotorId()));
            projectsRepsonseList.add(projectsResponseModel);
        }

        // ArrayList<TasksEntity> tasksEntities = tasksController.index();

        return (ArrayList<ProjectsResponseModel>) projectsRepsonseList;
    }

    @GetMapping(path = "/{id}")
    public Optional<ProjectEntity> get(@PathVariable("id") Long id) {
        return this.projectsService.getService(id);
    }

    @PostMapping()
    public ProjectEntity post(@RequestBody ProjectEntity project) {
        return this.projectsService.postService(project);
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        boolean ok = this.projectsService.deleteService(id);
        if (ok) {
            return "Delete task " + id;
        } else {
            return "Failed delete task" + id;
        }
    }

    ArrayList<TaskEntity> getTasks(long id) {
        return tasksController.getTasksByProjectId(id);
    }

    UserEntity getPromotor(long id) {
        return userController.get(id).get();
    }

}
