package com.lulosys.projectManager.ModelResponses;

import com.lulosys.projectManager.entitys.TaskEntity;

public class CreateTaskModel {
    TaskEntity task;
    Long employee_id;


    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }
}
