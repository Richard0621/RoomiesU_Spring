package com.roomies.roomiesu.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateUsuarioDTO {
    private Long id;
    private String username;
    private String nombre;
    private String apellido;
    private Integer identificacion;
    private String correo;
    private Integer telefono;
    private Integer edad;
    private String descripcion;
    private Set<String> roles; // Se reciben como Strings (ej. "ADMIN", "ESTUDIANTE")
}
