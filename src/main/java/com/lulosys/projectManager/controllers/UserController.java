package com.lulosys.projectManager.controllers;

import java.util.ArrayList;
import java.util.Optional;

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

    @PostMapping()
    public UserEntity post(@RequestBody UserEntity usuario) {
        return this.userService.postService(usuario);
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

}