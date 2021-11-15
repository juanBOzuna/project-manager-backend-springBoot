package com.lulosys.projectManager.entitys;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class TaskEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "project_id", length = 150)
    private long projectId;

    @Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
    private boolean is_completed = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public boolean getIsCompleted() {
        return is_completed;
    }

    public void setIsCompleted(boolean enabled) {
        this.is_completed = enabled;
    }

    public boolean isIs_completed() {
        return is_completed;
    }

    public void setIs_completed(boolean is_completed) {
        this.is_completed = is_completed;
    }

}
