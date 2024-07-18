package com.api.proyectos.proyectManager.services;

import com.api.proyectos.proyectManager.entities.LocalUser;
import com.api.proyectos.proyectManager.entities.Project;
import com.api.proyectos.proyectManager.models.CreateProjectBody;
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

    public Project createProject(CreateProjectBody projectBody){

        Project project = new Project();
        project.setName(projectBody.getName());
        project.setDescription(projectBody.getDescription());
        project.setStartDate(projectBody.getStartDate());
        project.setEndDate(projectBody.getEndDate());

        List<LocalUser> users = projectBody.getEquipo().stream()
                .map(id -> userRepository.findById(id).orElseThrow(() -> new  RuntimeException("User not found with id: " + id)))
                .collect(Collectors.toList());

//        for (LocalUser user : users) {
//            proyect.addTeamMember(user);
//        }
        project.setTeam(users);

        return projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    public List<Project> allProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    public Project findProject(Long id){
        return projectRepository.findById(id).get();
    }

}
