package com.api.proyectos.proyectManager.repositories;

import com.api.proyectos.proyectManager.entities.LocalUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<LocalUser, Long> {
}
