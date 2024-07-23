package com.api.proyectos.proyectManager.services;

import com.api.proyectos.proyectManager.entities.Activity;
import com.api.proyectos.proyectManager.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity createActivity(Activity activity){
        return activityRepository.save(activity);
    }

    public List<Activity> allActivities(){
        return (List<Activity>) activityRepository.findAll();
    }
}
