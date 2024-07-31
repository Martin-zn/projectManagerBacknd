package com.api.proyectos.proyectManager.repositories;

import com.api.proyectos.proyectManager.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProjectRepository extends CrudRepository<Project, Long> {

//    @Query("SELECT DISTINCT p FROM Proyect p LEFT JOIN FETCH p.team")
//    List<Project> findAllWithTeam();

    @Query("SELECT p FROM Project p JOIN p.team t WHERE t.id = :userId")
    List<Project> findProjectsByUserId(@Param("userId") Long userId);



}
