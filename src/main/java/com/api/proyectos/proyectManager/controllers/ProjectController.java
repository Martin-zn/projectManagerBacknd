package com.api.proyectos.proyectManager.controllers;

import com.api.proyectos.proyectManager.entities.LocalUser;
import com.api.proyectos.proyectManager.models.CreateProjectBody;
import com.api.proyectos.proyectManager.services.ProjectService;
import com.api.proyectos.proyectManager.services.UserDetailService;
import com.api.proyectos.proyectManager.services.UserService;
import com.api.proyectos.proyectManager.util.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserDetailService userService;



    @PostMapping("/create")
    public ResponseEntity createProject(@RequestBody CreateProjectBody project){

        return ResponseEntity.ok(projectService.createProject(project));
    }

    @GetMapping("/admin")
    @CrossOrigin("http://localhost:5173")
    public ResponseEntity allProjects(){
        return ResponseEntity.ok(projectService.allProjects());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.ok("Proyecto elimiando ");
    }

    @GetMapping("")
    @CrossOrigin("http://localhost:5173")
    public ResponseEntity allMyProjects(@RequestHeader("Authorization") String jwt){
        try{
            String newJwt = jwt.substring(7);
            LocalUser user = userService.findUserByJwt(newJwt);

            return ResponseEntity.ok(projectService.allMyProjects(user));

        }catch (RuntimeException e){
            System.out.println("Aca esta el error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
