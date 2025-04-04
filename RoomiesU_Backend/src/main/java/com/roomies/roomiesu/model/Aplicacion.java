package com.roomies.roomiesu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "aplicacion")
public class Aplicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuarioEstudiante")
    private Usuario estudiante;

    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;


    //Pillar si trabajasmo estados como aplico o no (boolean) o si hay más estados para crear una tabla de estados
    @Column(name = "estado")
    private String estado;



}
