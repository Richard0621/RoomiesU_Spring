package com.roomies.roomiesu.service;

import com.roomies.roomiesu.model.Habitacion;
import com.roomies.roomiesu.repository.HabitacionRepositorio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepositorio habitacionRepository;

    // Listar todas las habitaciones
    public List<Habitacion> getAllHabitaciones() {
        return habitacionRepository.findAll();
    }

    // Buscar una habitación por ID
    public Habitacion getHabitacionById(Long id) {
        return habitacionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Habitación no encontrada con id: " + id));
    }

    // Crear una nueva habitación
    public Habitacion createHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    // Actualizar una habitación existente
    public Habitacion updateHabitacion(Long id, Habitacion habitacionDetails) {
        if (!habitacionRepository.existsById(id)) {
            throw new EntityNotFoundException("Habitación no encontrada con id: " + id);
        }
        Habitacion habitacion = getHabitacionById(id);
        if (habitacionDetails.getPrecio() != null) habitacion.setPrecio(habitacionDetails.getPrecio());
        if (habitacionDetails.getDescripcion() != null) habitacion.setDescripcion(habitacionDetails.getDescripcion());
        if (habitacionDetails.getRequisitos() != null) habitacion.setRequisitos(habitacionDetails.getRequisitos());
        if (habitacionDetails.getUnidadVivienda() != null) habitacion.setUnidadVivienda(habitacionDetails.getUnidadVivienda());
        return habitacionRepository.save(habitacion);
    }

    // Eliminar una habitación
    public void deleteHabitacion(Long id) {
        habitacionRepository.deleteById(id);
    }
}