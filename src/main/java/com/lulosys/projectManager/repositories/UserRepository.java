package com.lulosys.projectManager.repositories;

import java.util.ArrayList;

import com.lulosys.projectManager.entitys.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    // public abstract ArrayList<UsuarioModel> findByPrioridad(Integer prioridad);
    public abstract UserEntity findByTaskId(Long taskId);
    public abstract UserEntity findByProjectId(Long projectId);
    public abstract ArrayList<UserEntity> findByRole(String role);

}