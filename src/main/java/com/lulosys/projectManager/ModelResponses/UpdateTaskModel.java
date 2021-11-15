package com.lulosys.projectManager.ModelResponses;

import com.lulosys.projectManager.entitys.TaskEntity;

public class UpdateTaskModel {
    CreateTaskModel createTaskModel;
    Long id_oldUserAsigned;
    Boolean completed;

    public Long getId_userAsigned() {
        return id_oldUserAsigned;
    }

    public void setId_userAsigned(Long id_userAsigned) {
        this.id_oldUserAsigned = id_userAsigned;
    }

    public CreateTaskModel getCreateTaskModel() {
        return createTaskModel;
    }

    public void setCreateTaskModel(CreateTaskModel createTaskModel) {
        this.createTaskModel = createTaskModel;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

}
