package com.api.proyectos.proyectManager.services;

import com.api.proyectos.proyectManager.entities.Activity;
import com.api.proyectos.proyectManager.entities.LocalUser;
import com.api.proyectos.proyectManager.entities.Project;
import com.api.proyectos.proyectManager.models.CreateProjectBody;
import com.api.proyectos.proyectManager.repositories.ActivityRepository;
import com.api.proyectos.proyectManager.repositories.ProjectRepository;
import com.api.proyectos.proyectManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public Project createProject(CreateProjectBody projectBody){

        Project project = new Project();
        project.setName(projectBody.getName());
        project.setDescription(projectBody.getDescription());
        project.setStartDate(projectBody.getStartDate());
        project.setEndDate(projectBody.getEndDate());

        List<LocalUser> users = projectBody.getEquipo().stream()
                .map(id -> userRepository.findById(id).orElseThrow(() -> new  RuntimeException("User not found with id: " + id)))
                .collect(Collectors.toList());

        project.setTeam(users);

        List<Activity> activities = projectBody.getActividades().stream()
                .map(activityBody -> {
                    Activity activity = new Activity();
                    activity.setName(activityBody.getName());
                    activity.setDescription(activityBody.getDescription());
                    activity.setEstado("pendiente");
                    activity.setProject(project);
                    return activity;
                })
                .collect(Collectors.toList());

        project.setActivities(activities);

        return projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    public List<Project> allProjects() {
        return (List<Project>) projectRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Project> allMyProjects(LocalUser user) {
        return (List<Project>) projectRepository.findProjectsByUserId(user.getId());
    }


    public Project findProject(Long id){
        return projectRepository.findById(id).get();
    }

    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }

}
