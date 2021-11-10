package com.lulosys.projectManager.services;

import java.util.ArrayList;
import java.util.Optional;

import com.lulosys.projectManager.entitys.ProjectEntity;
import com.lulosys.projectManager.repositories.ProjectsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectsService {
    @Autowired
    ProjectsRepository projectsRepository;

    public ArrayList<ProjectEntity> indexService() {
        return (ArrayList<ProjectEntity>) projectsRepository.findAll();
    }

    public ProjectEntity postService(ProjectEntity project) {
        return projectsRepository.save(project);
    }

    public Optional<ProjectEntity> getService(Long id) {
        return projectsRepository.findById(id);
    }
    
    public boolean deleteService(Long id) {
        try {
            projectsRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

}
