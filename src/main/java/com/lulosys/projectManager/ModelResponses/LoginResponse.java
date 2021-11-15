package com.lulosys.projectManager.ModelResponses;

import java.util.ArrayList;

import com.lulosys.projectManager.entitys.ProjectEntity;
import com.lulosys.projectManager.entitys.UserEntity;

public class LoginResponse {
    String error;
    UserEntity userEntity = new UserEntity();
    ProjectEntity project;

    public String getErrors() {
        return error;
    }

    public void setErrors(String error) {
        this.error = error;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

}
