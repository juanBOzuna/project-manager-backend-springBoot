package com.lulosys.projectManager.ModelResponses;

import com.lulosys.projectManager.entitys.UserEntity;

public class CreateUserModel {
    UserEntity user;
    String date;
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
