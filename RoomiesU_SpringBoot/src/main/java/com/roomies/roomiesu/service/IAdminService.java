package com.roomies.roomiesu.service;

import com.roomies.roomiesu.model.Administrador;

import java.util.List;

public interface IAdminService {

    List<Administrador> getAllAdmins();

    Administrador getAdminById(Long id);

    Administrador createAdmin(Administrador admin);

    int deleteAdmin(Long id);
}
