package com.roomies.roomiesu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "renta")
public class Renta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio")
    private LocalDate fecha_inicio;

    @Column(name = "fecha_fin")
    private LocalDate fecha_fin;

    @Column(name = "fecha_pago")
    private LocalDate fecha_pago;

    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "id_usuarioEstudiante")
    private Usuario estudiante;

    @ManyToOne
    @JoinColumn(name = "id_usuarioAdministrador")
    private Usuario administrador;

    //Revisar si lo manejamos boolean o si hay más estados para crear una tabla de estados
    @Column(name = "estado")
    private String estado;




}
