package com.roomies.roomiesu.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolesController {
    //Eejmplos de preautorizacion de roles hasAnyRole("ADMIN", "ESTUDIANTE") o hasRole("ADMIN") or hasRole("ESTUDIANTE")

    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN_PENSION')") // Se puede acceder con ambos roles
    public String accessAdmin() {
        return "Hola, has accedido como ADMIN_PENSION";
    }

    @GetMapping("/accessEstudiante")
    @PreAuthorize("hasRole('ESTUDIANTE')") //
    public String accessEstudiante() {
        return "Hola, has accedido como ESTUDIANTE";
    }
}
