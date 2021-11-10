package com.lulosys.projectManager.ModelResponses;

import java.util.ArrayList;
import com.lulosys.projectManager.entitys.*;

public class ProjectsResponseModel {
    private ProjectsEntity project;
    private ArrayList<TaskEntity> tasks;

    public ProjectsEntity getProject() {
        return project;
    }

    public void setProject(ProjectsEntity project) {
        this.project = project;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
