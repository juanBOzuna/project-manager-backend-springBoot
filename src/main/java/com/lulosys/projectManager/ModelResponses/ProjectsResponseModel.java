package com.lulosys.projectManager.ModelResponses;

import java.util.ArrayList;
import com.lulosys.projectManager.entitys.*;

public class ProjectsResponseModel {
    private ProjectEntity project;
    private UserEntity promotor;
    private ArrayList<TaskEntity> tasks;

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public UserEntity getPromotor() {
        return promotor;
    }

    public void setPromotor(UserEntity promotor) {
        this.promotor = promotor;
    }
}
