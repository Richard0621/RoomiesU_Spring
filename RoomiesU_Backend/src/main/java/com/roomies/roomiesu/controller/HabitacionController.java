package com.roomies.roomiesu.controller;

import com.roomies.roomiesu.model.Habitacion;
import com.roomies.roomiesu.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    // Obtener todas las habitaciones
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN_PENSION','ESTUDIANTE', 'SUPER_ADMIN')")
    public ResponseEntity<List<Habitacion>> getAllHabitaciones() {
        return ResponseEntity.ok(habitacionService.getAllHabitaciones());
    }

    // Obtener una habitación por ID
    @GetMapping("/list/{id}")
    @PreAuthorize("hasAnyRole('ADMIN_PENSION','ESTUDIANTE', 'SUPER_ADMIN')")
    public ResponseEntity<Habitacion> getHabitacionById(@PathVariable Long id) {
        return ResponseEntity.ok(habitacionService.getHabitacionById(id));
    }

    // Crear una nueva habitación
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN_PENSION', 'SUPER_ADMIN')")
    public ResponseEntity<Habitacion> createHabitacion(@RequestBody Habitacion habitacion) {
        return ResponseEntity.ok(habitacionService.createHabitacion(habitacion));
    }

    // Actualizar habitación existente
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN_PENSION', 'SUPER_ADMIN')")

    public ResponseEntity<Habitacion> updateHabitacion(@RequestBody Habitacion habitacion) {
        return ResponseEntity.ok(habitacionService.updateHabitacion(habitacion.getId(), habitacion));
    }

    // Eliminar habitación por ID
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN_PENSION', 'SUPER_ADMIN')")
    public ResponseEntity<Void> deleteHabitacion(@PathVariable Long id) {
        habitacionService.deleteHabitacion(id);
        return ResponseEntity.noContent().build();
    }
}

