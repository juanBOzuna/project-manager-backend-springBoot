package com.lulosys.projectManager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import com.lulosys.projectManager.entitys.TaskEntity;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
    public abstract ArrayList<TaskEntity> findByProjectId(Long projectId);
    // public abstract ArrayList<TaskEntity> findByProjectId(Long projectId);
}
