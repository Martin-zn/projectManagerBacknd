package com.api.proyectos.proyectManager.repositories;

import com.api.proyectos.proyectManager.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProjectRepository extends CrudRepository<Project, Long> {

//    @Query("SELECT DISTINCT p FROM Proyect p LEFT JOIN FETCH p.team")
//    List<Project> findAllWithTeam();



}
