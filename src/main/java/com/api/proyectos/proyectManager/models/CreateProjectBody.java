package com.api.proyectos.proyectManager.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateProjectBody {

    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    private List<Long> equipo;

    private List<Long> actividades;
}
