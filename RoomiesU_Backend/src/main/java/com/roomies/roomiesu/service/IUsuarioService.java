package com.roomies.roomiesu.service;

import com.roomies.roomiesu.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> getAllUsers();

    Usuario getUserById(Long id);

    Usuario createUser(Usuario usuario);

    int deleteUsuario(Long id);

}
