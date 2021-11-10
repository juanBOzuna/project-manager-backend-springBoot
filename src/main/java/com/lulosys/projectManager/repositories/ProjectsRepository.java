package com.lulosys.projectManager.repositories;
import com.lulosys.projectManager.entitys.ProjectsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends CrudRepository<ProjectsEntity, Long> {
    // @Query(value = "SELECT projects.*, tasks.name FROM projects JOIN tasks ON projects.id = tasks.project_id",nativeQuery = true )
}
