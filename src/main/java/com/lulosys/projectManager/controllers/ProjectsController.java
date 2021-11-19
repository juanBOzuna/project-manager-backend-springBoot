package com.lulosys.projectManager.controllers;

import java.sql.Timestamp;
import java.util.*;

import com.lulosys.projectManager.ModelResponses.CreateProjectModel;
import com.lulosys.projectManager.ModelResponses.CreateUserModel;
import com.lulosys.projectManager.ModelResponses.ProjectsResponseModel;
import com.lulosys.projectManager.ModelResponses.TaskResponseModel;
import com.lulosys.projectManager.entitys.DocumentEntity;
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
    @Autowired
    DocumentsController documentsController;

    @GetMapping()
    public ArrayList<ProjectsResponseModel> index() {
        ArrayList<ProjectsResponseModel> projectsRepsonseList = new ArrayList<ProjectsResponseModel>();
        ProjectsResponseModel projectsResponseModel;

        for (ProjectEntity project : projectsService.indexService()) {
            projectsResponseModel = new ProjectsResponseModel();
            projectsResponseModel.setProject(project);
            projectsResponseModel.setTasks(getTasks(project.getId()));
            try {

                projectsResponseModel.setPromotor(userController.getPromotorByProjectId(project.getId()));

            } catch (Exception e) {
                // TODO: handle exception
            }
            projectsResponseModel.setPercentageCompleted(calculatePercentageCompleted(getTasks(project.getId())));
            projectsRepsonseList.add(projectsResponseModel);
        }
        return (ArrayList<ProjectsResponseModel>) projectsRepsonseList;
    }

    @GetMapping(path = "/{id}")
    public Optional<ProjectEntity> get(@PathVariable("id") Long id) {
        return this.projectsService.getService(id);
    }

    // @PostMapping()
    // public ProjectEntity post(@RequestBody ProjectEntity project) {
    // return this.projectsService.postService(project);
    // }

    @PostMapping()
    public ProjectEntity post(@RequestBody CreateProjectModel project) {
        Timestamp date_init = Timestamp.valueOf(project.getDate_init());
        Timestamp date_finish = Timestamp.valueOf(project.getDate_finish());
        UserEntity promotor;
        project.getProject().setDate_init(date_init);
        project.getProject().setDate_finish(date_finish);

        ProjectEntity projectPost = this.projectsService.postService(project.getProject());
        System.out.println("entrada");
        try {
            promotor = userController.get(project.getPromotor_id()).get();
            System.out.println("entrada3");
            promotor.setProjectId(projectPost.getId());
            CreateUserModel userSetProject = new CreateUserModel();
            userSetProject.setUser(promotor);
            userController.post(userSetProject);
            System.out.println("promotor id project = " + promotor.getProjectId());
            System.out.println("promotor id project = " + promotor.getName());
            System.out.println("promotor id project = " + project.getProject().getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        return projectPost;
    }

    @GetMapping(path = "finalize/projectId={projectId}")
    public Boolean finalize(@PathVariable("projectId") Long projectId) {
        Boolean finalize = false;
        try {
            ArrayList<UserEntity> usersOfProject = userController.getUsersByProjectId(projectId);

            for (UserEntity userEntity : usersOfProject) {
                CreateUserModel userEdit = new CreateUserModel();
                userEntity.setTaskId(null);
                userEntity.setProjectId(null);
                userEdit.setUser(userEntity);
                userController.post(userEdit);
            }

            try {

                ProjectEntity project = projectsService.getService(projectId).get();
                Long datetime = System.currentTimeMillis();
                Timestamp timestamp = new Timestamp(datetime);
                project.setDate_finish(timestamp);
                project.setIs_completed(true);
                projectsService.postService(project);

            } catch (Exception e) {
                // TODO: handle exception
            }

            finalize = true;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return finalize;
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable("id") Long id) {

        try {
            try {
                System.out.print("\n\n\nEntrada delete project 1:");
                UserEntity promotor = userController.getPromotorByProjectId(id);
                promotor.setProjectId(null);
                System.out.print("\n\n\nPRomtor:" + promotor.getName());

                CreateUserModel userRemoveProject = new CreateUserModel();
                userRemoveProject.setUser(promotor);
                userController.post(userRemoveProject);
            } catch (Exception e) {
                // TODO: handle exception
            }

            ArrayList<TaskResponseModel> tasks = getTasks(id);
            try {
                for (TaskResponseModel taskResponseModel : tasks) {
                    System.out.println("Nombre" + taskResponseModel.getTask().getName());
                    tasksController.delete(taskResponseModel.getTask().getId());
                    UserEntity promotor = userController.getUserByTaskId(taskResponseModel.getTask().getId());
                    promotor.setTaskId(null);
                    CreateUserModel userRemoveTask = new CreateUserModel();
                    userRemoveTask.setUser(promotor);
                    promotor = userController.post(userRemoveTask);

                    try {
                        for (DocumentEntity documents : taskResponseModel.getDocuments()) {
                            documentsController.delete(documents.getId());
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        boolean ok = this.projectsService.deleteService(id);
        if (ok) {
            return "Delete  " + id;
        } else {
            return "Failed " + id;
        }
    }

    ArrayList<TaskResponseModel> getTasks(long id) {
        ArrayList<TaskResponseModel> lResponseModels = new ArrayList<TaskResponseModel>();
        TaskResponseModel taskResponseModel;
        for (TaskEntity task : tasksController.getTasksByProjectId(id)) {
            taskResponseModel = new TaskResponseModel();
            taskResponseModel.setTask(task);
            taskResponseModel.setDocuments(getDocumentsOfTask(task));
            taskResponseModel.setEmployeeAssign(getEmployeeAssign(task.getId()));
            lResponseModels.add(taskResponseModel);
        }
        return lResponseModels;
    }

    UserEntity getEmployeeAssign(Long taskId) {
        return userController.getUserByTaskId(taskId);

    }

    ArrayList<DocumentEntity> getDocumentsOfTask(TaskEntity taskEntity) {
        return documentsController.getDocumentsByTaskId(taskEntity.getId());
    }

    UserEntity getPromotor(long id) {
        try {
            return userController.get(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    float calculatePercentageCompleted(ArrayList<TaskResponseModel> tasks) {
        float large = tasks.size();
        float taskscompleted = 0;

        for (TaskResponseModel taskResponseModel : tasks) {
            boolean val = taskResponseModel.getTask().getIsCompleted();
            if (val) {
                taskscompleted++;
            }
        }

        float perc = (taskscompleted / large) * 100;

        return perc;

        // try {

        // // this.setPercentageCompleted(taskscompleted);
        // } catch (Exception e) {
        // return 0.0;
        // // TODO: handle exception
        // }

    }

}
