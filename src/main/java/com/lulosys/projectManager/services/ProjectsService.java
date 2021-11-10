package com.lulosys.projectManager.services;

import java.util.ArrayList;
import java.util.Optional;

import com.lulosys.projectManager.entitys.ProjectsEntity;
import com.lulosys.projectManager.repositories.ProjectsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectsService {
    @Autowired
    ProjectsRepository projectsRepository;

    public ArrayList<ProjectsEntity> indexService() {
        return (ArrayList<ProjectsEntity>) projectsRepository.findAll();
    }

    public ProjectsEntity postService(ProjectsEntity project) {
        return projectsRepository.save(project);
    }

    public Optional<ProjectsEntity> getService(Long id) {
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
