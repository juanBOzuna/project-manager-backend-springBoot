package com.lulosys.projectManager.controllers;

import java.util.*;

import com.lulosys.projectManager.ModelResponses.ProjectsResponseModel;
import com.lulosys.projectManager.entitys.ProjectsEntity;
import com.lulosys.projectManager.entitys.TaskEntity;
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

    @GetMapping()
    public ArrayList<ProjectsResponseModel> index() {
        ArrayList<ProjectsResponseModel> projectsRepsonseList = new ArrayList<ProjectsResponseModel>();
        ProjectsResponseModel projectsResponseModel;
        // ArrayList<ProjectsEntity> projectsEntities = projectsService.indexService();

        for (ProjectsEntity project : projectsService.indexService()) {
            projectsResponseModel = new ProjectsResponseModel();
            projectsResponseModel.setProject(project);
            projectsResponseModel.setTasks(getTasks(project.getId()));
            projectsRepsonseList.add(projectsResponseModel);
        }

        // ArrayList<TasksEntity> tasksEntities = tasksController.index();

        return (ArrayList<ProjectsResponseModel>) projectsRepsonseList;
    }

    @GetMapping(path = "/{id}")
    public Optional<ProjectsEntity> get(@PathVariable("id") Long id) {
        return this.projectsService.getService(id);
    }

    @PostMapping()
    public ProjectsEntity post(@RequestBody ProjectsEntity project) {
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

}
