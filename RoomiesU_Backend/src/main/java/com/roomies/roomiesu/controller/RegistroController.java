package com.roomies.roomiesu.controller;

import com.roomies.roomiesu.controller.request.CreateUsuarioDTO;
import com.roomies.roomiesu.model.ERol;
import com.roomies.roomiesu.model.RolModelo;
import com.roomies.roomiesu.model.Usuario;
import com.roomies.roomiesu.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registro público de estudiantes o admin_pension
    @PostMapping("/register")
    public ResponseEntity<Usuario> registerUser(@Valid @RequestBody CreateUsuarioDTO userDto) {
        // Validar si el usuario ya existe
        if (usuarioService.getAllUsers().stream().anyMatch(user -> user.getUsername().equals(userDto.getUsername()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya está en uso");
        }
        // Asignar rol por defecto si no se especifica
        Set<RolModelo> roles = new HashSet<>();
        if (userDto.getRoles() == null || userDto.getRoles().isEmpty()) {
            roles.add(RolModelo.builder().name(ERol.ESTUDIANTE).build()); // Rol por defecto
        } else {
            roles = userDto.getRoles().stream()
                    .map(role -> {
                        if (role.equals("SUPER_ADMIN")) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No puedes registrarte como SUPER_ADMIN");
                        }
                        return RolModelo.builder().name(ERol.valueOf(role)).build();
                    })
                    .collect(Collectors.toSet());
        }

        // Crear usuario
        Usuario usuario = Usuario.builder()
                .username(userDto.getUsername())
                .nombre(userDto.getNombre())
                .apellido(userDto.getApellido())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .identificacion(userDto.getIdentificacion())
                .correo(userDto.getCorreo())
                .telefono(userDto.getTelefono())
                .edad(userDto.getEdad())
                .telefono(userDto.getTelefono())
                .descripcion(userDto.getDescripcion())
                .isAvailable(Boolean.TRUE)
                .roles(roles)
                .build();

        usuarioService.createUser(usuario); // Guardar usuario en la base de datos
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
}
