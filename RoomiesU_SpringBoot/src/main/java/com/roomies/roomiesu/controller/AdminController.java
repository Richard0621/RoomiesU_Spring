package com.roomies.roomiesu.controller;

import com.roomies.roomiesu.model.Administrador;
import com.roomies.roomiesu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    //Listar todos los administradores
    @GetMapping("/list")
    public List<Administrador> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    //Listar un adminstrador por id
    @GetMapping("/list/{id}")
    public Administrador getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    //Crear un administrador
    @PostMapping
    public ResponseEntity<Administrador> createAdmin(@RequestBody Administrador admin) {
        Administrador administrador = adminService.createAdmin(admin);
        return new ResponseEntity<>(administrador, HttpStatus.CREATED);
    }

    //Actualizar un administrador
    @PutMapping
    public ResponseEntity<Administrador> updateAdmin(@RequestBody Administrador admin) {
        Administrador administrador = adminService.getAdminById(admin.getId());
        if(administrador != null){
            administrador.setNombre(admin.getNombre());
            administrador.setApellido(admin.getApellido());
            administrador.setIdentificacion(admin.getIdentificacion());
            administrador.setCorreo(admin.getCorreo());
            administrador.setTelefono(admin.getTelefono());
            administrador.setEdad(admin.getEdad());
            administrador.setDescripcion(admin.getDescripcion());
            adminService.createAdmin(administrador);
        } else {
            return new ResponseEntity<>(administrador, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(administrador, HttpStatus.OK);
    }

    //Eliminar un administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<Administrador> deleteAdmin(@PathVariable Long id) {
        Administrador administrador = adminService.getAdminById(id);
        if(administrador != null){
            adminService.deleteAdmin(id);
        } else {
            return new ResponseEntity<>(administrador, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(administrador, HttpStatus.OK);
    }

}
