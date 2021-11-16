package com.lulosys.projectManager.services;

import java.util.ArrayList;
import java.util.Optional;

import com.lulosys.projectManager.controllers.TaskController;
import com.lulosys.projectManager.entitys.TaskEntity;
import com.lulosys.projectManager.entitys.UserEntity;
import com.lulosys.projectManager.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskController TaskController;

    public ArrayList<UserEntity> indexService() {
        try {
            return (ArrayList<UserEntity>) userRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    public TaskEntity getTaskOfEmployeeService(Long id) {
        TaskEntity task = null;
        try {
            UserEntity user = userRepository.findById(id).get();

            task = TaskController.get(user.getTaskId()).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return task;
    }

    public UserEntity postService(UserEntity user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    public Optional<UserEntity> obtenerPorId(Long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public UserEntity getByTaskIdService(long taskId) {
        try {
            return userRepository.findByTaskId(taskId);

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public UserEntity getByEmailService(String email) {
        try {
            return userRepository.findByEmail(email);

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public ArrayList<UserEntity> getByProjectIdService(long projectId) {
        return this.userRepository.findByProjectId(projectId);
    }

    public UserEntity getPromotorByProjectIdService(long projectId) {
        try {
            UserEntity userPromotor = null;
            for (UserEntity user : this.userRepository.findByProjectId(projectId)) {
                if (user.getRole().equals("promotor")) {
                    userPromotor = user;
                }
            }
            return userPromotor;

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public ArrayList<UserEntity> getByRoleService(String role) {
        try {
            return userRepository.findByRole(role);

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public boolean deleteService(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}