package com.roomies.roomiesu.repository;

import com.roomies.roomiesu.model.Aplicacion;
import com.roomies.roomiesu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AplicacionRepositorio extends JpaRepository<Aplicacion, Long> {

}
