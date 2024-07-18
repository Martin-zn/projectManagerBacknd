package com.api.proyectos.proyectManager.controllers;

import com.api.proyectos.proyectManager.entities.LocalUser;
import com.api.proyectos.proyectManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUSer(@RequestBody LocalUser user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity findUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("")
    public ResponseEntity findAllUsers(){
        return ResponseEntity.ok(userService.findUsers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUSer(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Usuario eliminado");
    }


}
