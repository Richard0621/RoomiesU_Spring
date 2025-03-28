package com.roomies.roomiesu.controller;

import com.roomies.roomiesu.model.Administrador;
import com.roomies.roomiesu.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/admin")
public class AdminController {

    AdminService adminService;

    @GetMapping("/list")
    public List<Administrador> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/list/{id}")
    public Administrador getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    

}
