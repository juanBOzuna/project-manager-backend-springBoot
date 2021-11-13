package com.lulosys.projectManager.services;

import java.util.ArrayList;
import java.util.Optional;

import com.lulosys.projectManager.entitys.UserEntity;
import com.lulosys.projectManager.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<UserEntity> indexService() {
        try {
            return (ArrayList<UserEntity>) userRepository.findAll();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

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

    public UserEntity getByProjectIdService(long projectId) {
        try {
            return userRepository.findByProjectId(projectId);

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