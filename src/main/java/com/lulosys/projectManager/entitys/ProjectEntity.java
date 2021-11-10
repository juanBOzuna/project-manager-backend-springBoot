package com.lulosys.projectManager.entitys;

import javax.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
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

    @Column(name = "date_init", nullable = true, length = 150)
    private Timestamp date_init;

    @Column(name = "date_finish", nullable = true, length = 150)
    private Timestamp date_finish;

    @Column(name = "promotor_id", length = 150, nullable = true)
    private Long promotor_id;

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

    public Long getPromotorId() {
        return promotor_id;
    }

    public void setPromotorId(Long promotor_id) {
        this.promotor_id = promotor_id;
    }

    // public List<TasksEntity> getTasks() {
    // return tasks;
    // }

    // public void setTask(TasksEntity task) {
    // this.tasks.add(task);
    // }
}
