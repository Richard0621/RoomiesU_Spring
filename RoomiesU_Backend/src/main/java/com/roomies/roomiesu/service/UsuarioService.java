package com.roomies.roomiesu.service;

import com.roomies.roomiesu.model.Usuario;
import com.roomies.roomiesu.repository.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepositorio userRespositorio;


    @Override
    public List<Usuario> getAllUsers() {
        return userRespositorio.findAll();
    }

    @Override
    public Usuario getUserById(Long id) {
        return userRespositorio.findById(id).orElse(null);
    }

    @Override
    public Usuario createUser(Usuario usuario) {
        return userRespositorio.save(usuario);
    }

    @Override
    public int deleteUsuario(Long id) {
        userRespositorio.deleteById(id);
        return 1;
    }

    @Override
    public Optional<Usuario> getUserByUsername(String username) {
        return userRespositorio.getUsername(username);
    }
}
