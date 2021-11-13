package com.lulosys.projectManager.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import com.lulosys.projectManager.ModelResponses.CreateUserModel;
import com.lulosys.projectManager.entitys.UserEntity;
import com.lulosys.projectManager.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UsersService userService;

    @GetMapping()
    public ArrayList<UserEntity> index() {
        return userService.indexService();
    }

    @GetMapping(path = "/{id}")
    public Optional<UserEntity> get(@PathVariable("id") Long id) {
        return this.userService.obtenerPorId(id);
    }

    @GetMapping(path = "/rol={rol}")
    public ArrayList<UserEntity> get(@PathVariable("rol") String rol) {
        return this.userService.getByRoleService(rol);
    }

    // @GetMapping(path = "/project={projectId}")
    public UserEntity getByProjectId(Long projectId) {
        return this.userService.getByProjectIdService(projectId);
    }

    @PostMapping()
    public UserEntity post(@RequestBody CreateUserModel usuario) {
        try {
            Timestamp date = Timestamp.valueOf(usuario.getDate());
            usuario.getUser().setHiring_date(date);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return this.userService.postService(usuario.getUser());
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@PathVariable("id") Long id) {
        boolean ok = this.userService.deleteService(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No pudo eliminar el usuario con id" + id;
        }
    }

    UserEntity getUserByTaskId(long taskId) {
        try {
            return userService.getByTaskIdService(taskId);
        } catch (Exception e) {
            UserEntity usernn = new UserEntity();
            usernn.setName("Sin nombre");
            usernn.setId(0L);
            // TODO: handle exception
            return usernn;
        }
    }

}