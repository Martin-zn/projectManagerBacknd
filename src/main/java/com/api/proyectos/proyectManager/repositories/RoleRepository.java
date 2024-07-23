package com.api.proyectos.proyectManager.repositories;

import com.api.proyectos.proyectManager.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity>findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}
