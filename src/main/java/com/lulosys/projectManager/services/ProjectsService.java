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
        try {
            return (ArrayList<ProjectEntity>) projectsRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public ProjectEntity postService(ProjectEntity project) {
        try {
            return projectsRepository.save(project);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public Optional<ProjectEntity> getService(Long id) {
        try {
            return projectsRepository.findById(id);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
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
