package com.roomies.roomiesu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "identificacion")
    private Integer identificacion;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "isAvailable")
    private Boolean isAvailable;

}
