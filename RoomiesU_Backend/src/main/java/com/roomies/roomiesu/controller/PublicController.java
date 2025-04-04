package com.roomies.roomiesu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PublicController {

    @GetMapping("/prueba")
    public String prueba() {
        return "Hola, esta es una prueba de la API REST de RoomiesU! sin seguridad";
    }


}
