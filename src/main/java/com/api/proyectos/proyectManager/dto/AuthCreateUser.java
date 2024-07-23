package com.api.proyectos.proyectManager.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUser(@NotBlank String username,
                             @NotBlank String name,
                             @NotBlank String lastname,
                             @NotBlank String email,
                             Integer number,
                             @NotBlank String password,
                             @Valid AuthCreateRoleRequest roleRequest) {
}
