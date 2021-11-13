package com.lulosys.projectManager.ModelResponses;

import java.util.ArrayList;
import com.lulosys.projectManager.entitys.*;

public class ProjectsResponseModel {
    private ProjectEntity project;
    private UserEntity promotor;
    private ArrayList<TaskResponseModel> tasks;
    private float percentageCompleted;

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public ArrayList<TaskResponseModel> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<TaskResponseModel> tasks) {
        this.tasks = tasks;
    }

    public UserEntity getpromotor() {
        return promotor;
    }

    public void setPromotor(UserEntity promotor) {
        this.promotor = promotor;
    }

    public float getPercentageCompleted() {
        return percentageCompleted;
    }

    public void setPercentageCompleted(float percentageCompleted) {
        this.percentageCompleted = percentageCompleted;
    }

    // public void calculatePercentageCompleted() {
    // int large = tasks.size();
    // int taskscompleted = 0;

    // for (TaskResponseModel taskResponseModel : tasks) {
    // if (taskResponseModel.getTask().getIsCompleted()) {
    // taskscompleted++;
    // }
    // }

    // try {
    // this.setPercentageCompleted((taskscompleted / large) * 100);
    // // this.setPercentageCompleted(taskscompleted);
    // } catch (Exception e) {
    // // TODO: handle exception
    // }

    // System.out.print("percentage : " + this.percentageCompleted);
    // }

}
