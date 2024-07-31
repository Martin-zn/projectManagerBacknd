package com.api.proyectos.proyectManager.controllers;

import com.api.proyectos.proyectManager.dto.AuthCreateUser;
import com.api.proyectos.proyectManager.dto.AuthLoginRequest;
import com.api.proyectos.proyectManager.dto.AuthResponse;
import com.api.proyectos.proyectManager.entities.LocalUser;
import com.api.proyectos.proyectManager.services.UserDetailService;
import com.api.proyectos.proyectManager.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;


    @PostMapping("/register")
    @CrossOrigin("http://localhost:5173")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody @Valid AuthCreateUser authCreateUser){
        return new ResponseEntity<>(this.userDetailService.regiterUser(authCreateUser), HttpStatus.OK);
    }

    @PostMapping("/log")
    @CrossOrigin("http://localhost:5173")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }













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
