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
        try {
            return (ArrayList<TaskEntity>) taskRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    public TaskEntity postService(TaskEntity task) {
        try {
            return taskRepository.save(task);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    public Optional<TaskEntity> getService(Long id) {
        try {
            return taskRepository.findById(id);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public ArrayList<TaskEntity> getByProjectIdService(long projectId) {
        try {
            return taskRepository.findByProjectId(projectId);

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
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
