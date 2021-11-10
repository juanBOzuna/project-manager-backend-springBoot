package com.lulosys.projectManager.repositories;
import com.lulosys.projectManager.entitys.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends CrudRepository<ProjectEntity, Long> {
    // @Query(value = "SELECT projects.*, tasks.name FROM projects JOIN tasks ON projects.id = tasks.project_id",nativeQuery = true )
}
