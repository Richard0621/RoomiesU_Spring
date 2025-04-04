package com.roomies.roomiesu.controller;

import com.roomies.roomiesu.controller.request.CreateUsuarioDTO;
import com.roomies.roomiesu.controller.request.UpdateUsuarioDTO;
import com.roomies.roomiesu.model.ERol;
import com.roomies.roomiesu.model.RolModelo;
import com.roomies.roomiesu.model.Usuario;
import com.roomies.roomiesu.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;


    public UserController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

/// ///////////////////////////////////////////////////
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ADMIN_PENSION','ESTUDIANTE', 'SUPER_ADMIN')")
    public ResponseEntity<List<Usuario>> getAllAdmins() {
        List<Usuario> usuarios = usuarioService.getAllUsers();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Si no hay usuarios responde con 204
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

/// ///////////////////////////////////////////////////////////////////

    //Listar un adminstrador por id
    @GetMapping("/list/{id}")
    @PreAuthorize("hasAnyRole('ADMIN_PENSION','ESTUDIANTE', 'SUPER_ADMIN')")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUserById(id);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si no se encuentra el usuario responde con 404
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    /// ////////////////////////////////////////////////////////////////
    //Crear un Usuario pero para REGISTER
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN_PENSION','ESTUDIANTE', 'SUPER_ADMIN')")
    public ResponseEntity<Usuario> createAdmin(@Valid @RequestBody CreateUsuarioDTO userDto) {
        // Validar si el correo ya existe
        if (usuarioService.getAllUsers().stream().anyMatch(user -> user.getEmail().equals(userDto.getEmail()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está en uso");
        }

        // Convertir los roles del DTO a entidades
        Set<RolModelo> roles = userDto.getRoles().stream()
                .map(role -> RolModelo.builder()
                        .name(ERol.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        // Crear una instancia de Usuario con todos los datos del DTO
        Usuario usuario = Usuario.builder()
                .username(userDto.getUsername())   
                .nombre(userDto.getNombre())       
                .apellido(userDto.getApellido())   
                .password(passwordEncoder.encode(userDto.getPassword()))
                .identificacion(userDto.getIdentificacion())   
                .email(userDto.getEmail())
                .telefono(userDto.getTelefono())               
                .edad(userDto.getEdad())                       
                .descripcion(userDto.getDescripcion())         
                .isAvailable(userDto.getIsAvailable())         
                .roles(roles)
                .build();

        usuarioService.createUser(usuario); // Guardar el usuario en la base de datos

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    /// /////////////////////////////////////////////////////////////////////////////////
    //Actualizar un Usuario
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN_PENSION','ESTUDIANTE', 'SUPER_ADMIN')")
    public ResponseEntity<Usuario> updateAdmin(@RequestBody UpdateUsuarioDTO userDto) {
        // Buscar el usuario en la base de datos
        Usuario usuario = usuarioService.getUserById(userDto.getId());

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Solo actualizar si el campo no es null (para evitar sobrescribir datos con valores vacíos)
        if (userDto.getUsername() != null) usuario.setUsername(userDto.getUsername());
        if (userDto.getNombre() != null) usuario.setNombre(userDto.getNombre());
        if (userDto.getApellido() != null) usuario.setApellido(userDto.getApellido());
        if (userDto.getIdentificacion() != null) usuario.setIdentificacion(userDto.getIdentificacion());
        if (userDto.getEmail() != null) usuario.setEmail(userDto.getEmail());
        if (userDto.getTelefono() != null) usuario.setTelefono(userDto.getTelefono());
        if (userDto.getEdad() != null) usuario.setEdad(userDto.getEdad());
        if (userDto.getDescripcion() != null) usuario.setDescripcion(userDto.getDescripcion());

        // Si el DTO incluye roles, convertirlos a entidades y actualizarlos
        if (userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            Set<RolModelo> roles = userDto.getRoles().stream()
                    .map(role -> RolModelo.builder().name(ERol.valueOf(role)).build())
                    .collect(Collectors.toSet());
            usuario.setRoles(roles);
        }

        // Guardar los cambios en la base de datos
        usuarioService.createUser(usuario);

        return ResponseEntity.ok(usuario);
    }

    /// ///////////////////////////////////////////////////////////////////7

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN_PENSION','ESTUDIANTE', 'SUPER_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUserById(id);

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

