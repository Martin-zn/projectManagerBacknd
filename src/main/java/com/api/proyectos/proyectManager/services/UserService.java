package com.api.proyectos.proyectManager.services;

import com.api.proyectos.proyectManager.entities.LocalUser;
import com.api.proyectos.proyectManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public LocalUser createUser(LocalUser user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public LocalUser findUserById(long id){
        return userRepository.findById(id).orElseThrow();
    }

    public List<LocalUser> findUsers(){
        return (List<LocalUser>) userRepository.findAll();
    }

}
