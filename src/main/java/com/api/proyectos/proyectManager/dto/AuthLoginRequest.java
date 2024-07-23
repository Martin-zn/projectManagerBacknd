package com.api.proyectos.proyectManager.dto;

import org.springframework.lang.NonNull;

public record AuthLoginRequest(@NonNull String username,
                               @NonNull String password) {
}
