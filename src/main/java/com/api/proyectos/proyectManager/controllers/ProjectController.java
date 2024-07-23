package com.api.proyectos.proyectManager.controllers;

import com.api.proyectos.proyectManager.models.CreateProjectBody;
import com.api.proyectos.proyectManager.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity createProject(@RequestBody CreateProjectBody project){

        return ResponseEntity.ok(projectService.createProject(project));
    }

    @GetMapping("")
    public ResponseEntity allProjects(){
        return ResponseEntity.ok(projectService.allProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity allProjects(@PathVariable Long id){
        return ResponseEntity.ok(projectService.findProject(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.ok("Proyecto elimiando ");
    }
}
