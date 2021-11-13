package com.lulosys.projectManager.ModelResponses;

import com.lulosys.projectManager.entitys.ProjectEntity;

public class CreateProjectModel {
    ProjectEntity project = new ProjectEntity();
    String date_init;
    String date_finish;
    Long promotor_id;

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getDate_init() {
        return date_init;
    }

    public void setDate_init(String date_init) {
        this.date_init = date_init;
    }

    public String getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(String date_finish) {
        this.date_finish = date_finish;
    }

    public Long getPromotor_id() {
        return promotor_id;
    }

    public void setPromotor_id(Long promotor_id) {
        this.promotor_id = promotor_id;
    }

}
