package com.roomies.roomiesu.service;

import com.roomies.roomiesu.model.Estudiante;
import com.roomies.roomiesu.repository.EstudianteRepositorio;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EstudianteService implements IEstudianteService {

    @Autowired
    EstudianteRepositorio estudianteRepositorio;

    @Override
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepositorio.findAll();
    }

    @Override
    public Estudiante getEstudianteById(Long id) {
        return estudianteRepositorio.findById(id).orElse(null);
    }

    @Override
    public Estudiante createEstudiante(Estudiante estudiante) {
        return estudianteRepositorio.save(estudiante);
    }

    @Override
    public int deleteEstudiante(Long id) {
        estudianteRepositorio.deleteById(id);
        return 1;
    }
}
