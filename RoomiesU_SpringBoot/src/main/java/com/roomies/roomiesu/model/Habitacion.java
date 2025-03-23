package com.roomies.roomiesu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="precio")
    private Double precio;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_unidad_vivienda")
    private UnidadVivienda unidadVivienda;

    @Column(name = "requisitos")
    private String requisitos;

    @ManyToOne
    @JoinColumn(name = "id_administrador")
    private Administrador administrador;



}
