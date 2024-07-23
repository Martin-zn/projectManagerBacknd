package com.api.proyectos.proyectManager.controllers;

import com.api.proyectos.proyectManager.entities.Activity;
import com.api.proyectos.proyectManager.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping("/create")
    public ResponseEntity createActivity(@RequestBody Activity activity){
        return ResponseEntity.ok(activityService.createActivity(activity));
    }

    @GetMapping("")
    public ResponseEntity allActivity(){
        return ResponseEntity.ok(activityService.allActivities());
    }
}
