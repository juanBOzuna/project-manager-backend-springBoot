package com.lulosys.projectManager.ModelResponses;

import java.util.ArrayList;

import com.lulosys.projectManager.entitys.DocumentEntity;
import com.lulosys.projectManager.entitys.TaskEntity;
import com.lulosys.projectManager.entitys.UserEntity;

public class TaskResponseModel {
    private TaskEntity task;
    private ArrayList<DocumentEntity> documents;
    private UserEntity employeeAssign;

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public ArrayList<DocumentEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<DocumentEntity> documents) {
        this.documents = documents;
    }

    public UserEntity getEmployeeAssign() {
        return employeeAssign;
    }

    public void setEmployeeAssign(UserEntity employeeAssign) {
        this.employeeAssign = employeeAssign;
    }

}
