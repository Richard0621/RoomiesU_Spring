package com.roomies.roomiesu.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateUsuarioDTO {

    @NotBlank(message = "El username no puede estar vacío")
    private String username;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    private String apellido;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private Integer identificacion;

    @Email(message = "Debe ser un email válido")
    private String email;

    private Integer telefono;
    private Integer edad;
    private String descripcion;
    private Boolean isAvailable;

    @NotEmpty(message = "Debe tener al menos un rol")
    private Set<String> roles;  // Se esperan valores como "ADMIN" o "ESTUDIANTE", etc. ["USER", "ADMIN"]
}
