package com.roomies.roomiesu.service;

import com.roomies.roomiesu.model.Estudiante;
import java.util.List;

public interface IEstudianteService {

    List<Estudiante> getAllEstudiantes();

    Estudiante getEstudianteById(Long id);

    Estudiante createEstudiante(Estudiante estudiante);

    int deleteEstudiante(Long id);

}
