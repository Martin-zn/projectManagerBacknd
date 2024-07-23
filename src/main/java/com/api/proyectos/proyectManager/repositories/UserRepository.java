package com.api.proyectos.proyectManager.repositories;

import com.api.proyectos.proyectManager.entities.LocalUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<LocalUser, Long> {

    public Optional<LocalUser> findByUsername(String username);
}
