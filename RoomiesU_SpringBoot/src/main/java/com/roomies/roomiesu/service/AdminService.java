package com.roomies.roomiesu.service;

import com.roomies.roomiesu.model.Administrador;
import com.roomies.roomiesu.repository.AdministradorRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdminService implements IAdminService {

    @Autowired
    AdministradorRepositorio adminRepositorio;

    @Override
    public List<Administrador> getAllAdmins() {
        return adminRepositorio.findAll();
    }

    @Override
    public Administrador getAdminById(Long id) {
        return adminRepositorio.findById(id).orElse(null);
    }

    @Override
    public Administrador createAdmin(Administrador admin) {
        return adminRepositorio.save(admin);
    }

    @Override
    public int deleteAdmin(Long id) {
        adminRepositorio.deleteById(id);
        return 1;
    }

}
