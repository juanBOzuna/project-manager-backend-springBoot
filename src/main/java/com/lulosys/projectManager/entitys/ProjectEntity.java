package com.lulosys.projectManager.entitys;

import javax.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "projects")
public class ProjectEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "key_name", length = 150)
    private String key_name;

    @Column(name = "comercial_designation", length = 150)
    private String comercial_designation;

    @Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
    private boolean is_completed = false;

    @Column(name = "date_init", nullable = true, length = 150)
    private Timestamp date_init;

    @Column(name = "date_finish", nullable = true, length = 150)
    private Timestamp date_finish;

    // @Column(name = "promotor_id", length = 150, nullable = true)
    // private Long promotor_id;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp created_at;

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

    public String getKey_name() {
        return key_name;
    }

    public void setKey_name(String key_name) {
        this.key_name = key_name;
    }

    public String getComercial_designation() {
        return comercial_designation;
    }

    public void setComercial_designation(String comercial_designation) {
        this.comercial_designation = comercial_designation;
    }

    public Timestamp getDate_init() {
        return date_init;
    }

    public void setDate_init(Timestamp date_init) {
        this.date_init = date_init;
    }

    public Timestamp getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(Timestamp date_finish) {
        this.date_finish = date_finish;
    }

    // public Long getPromotor_id() {
    // return promotor_id;
    // }

    // public void setPromotor_id(Long promotor_id) {
    // this.promotor_id = promotor_id;
    // }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public boolean isIs_completed() {
        return is_completed;
    }

    public void setIs_completed(boolean is_completed) {
        this.is_completed = is_completed;
    }

    // public List<TasksEntity> getTasks() {
    // return tasks;
    // }

    // public void setTask(TasksEntity task) {
    // this.tasks.add(task);
    // }
}
