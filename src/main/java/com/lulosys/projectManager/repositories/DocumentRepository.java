package com.lulosys.projectManager.repositories;

import java.util.ArrayList;

import com.lulosys.projectManager.entitys.DocumentEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<DocumentEntity, Long> {
    public abstract ArrayList<DocumentEntity> findByTaskId(Long taskId);
}
