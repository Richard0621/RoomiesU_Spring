package com.roomies.roomiesu.repository;

import com.roomies.roomiesu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

    @Query("select u from Usuario u where u.username = ?1")
    Optional<Usuario> getUsername(String username);
}
