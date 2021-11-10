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
    
    public ArrayList<UserEntity> indexService(){
        return (ArrayList<UserEntity>) userRepository.findAll();
    }

    public UserEntity postService(UserEntity user){
        return userRepository.save(user);
    }

    public Optional<UserEntity> obtenerPorId(Long id){
        return userRepository.findById(id);
    }

    // public ArrayList<UsuarioModel>  obtenerPorPrioridad(Integer prioridad) {
    //     return usuarioRepository.findByPrioridad(prioridad);
    // }

    public boolean deleteService(Long id) {
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}