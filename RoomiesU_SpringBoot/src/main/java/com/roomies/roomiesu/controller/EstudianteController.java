package com.roomies.roomiesu.controller;

import com.roomies.roomiesu.model.Estudiante;
import com.roomies.roomiesu.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    //Listar todos los estudiantes
    @GetMapping("/list")
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.getAllEstudiantes();
    }

    //Listar un estudiante por id
    @GetMapping("/list/{id}")
    public Estudiante getEstudianteById(@PathVariable Long id) {
        return estudianteService.getEstudianteById(id);
    }

    //Crear un estudiante
    @PostMapping
    public ResponseEntity<Estudiante> createEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante est = estudianteService.createEstudiante(estudiante);
        return new ResponseEntity<>(est, HttpStatus.CREATED);
    }

    //Actualizar un estudiante
    @PutMapping
    public ResponseEntity<Estudiante> updateEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante est = estudianteService.getEstudianteById(estudiante.getId());
        if(est != null){
            est.setNombre(estudiante.getNombre());
            est.setApellido(estudiante.getApellido());
            est.setIdentificacion(estudiante.getIdentificacion());
            est.setCorreo(estudiante.getCorreo());
            est.setTelefono(estudiante.getTelefono());
            est.setEdad(estudiante.getEdad());
            est.setDescripcion(estudiante.getDescripcion());
            estudianteService.createEstudiante(est);
        } else {
            return new ResponseEntity<>(est, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(est, HttpStatus.OK);
    }

    //Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Estudiante> deleteEstudiante(@PathVariable Long id) {
        Estudiante est = estudianteService.getEstudianteById(id);
        if(est != null){
            estudianteService.deleteEstudiante(id);
        } else {
            return new ResponseEntity<>(est, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(est, HttpStatus.OK);
    }

}
