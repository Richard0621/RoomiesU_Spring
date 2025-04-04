package com.roomies.roomiesu;

import com.roomies.roomiesu.model.ERol;
import com.roomies.roomiesu.model.RolModelo;
import com.roomies.roomiesu.model.Usuario;
import com.roomies.roomiesu.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class RoomiesUApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomiesUApplication.class, args);
    }
}


