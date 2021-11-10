package com.lulosys.projectManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import com.lulosys.projectManager.entitys.TaskEntity;
import com.lulosys.projectManager.repositories.TaskRepository;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public ArrayList<TaskEntity> indexService() {
        return (ArrayList<TaskEntity>) taskRepository.findAll();
    }

    public TaskEntity postService(TaskEntity task) {
        return taskRepository.save(task);
    }

    public Optional<TaskEntity> getService(Long id) {
        return taskRepository.findById(id);
    }

    public ArrayList<TaskEntity> getByProjectIdService(long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public boolean deleteService(Long id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
