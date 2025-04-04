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
@Table(name = "resena_estudiante")
public class ResenaEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creacion")
    private LocalDate fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "id_UsuarioEstudiante")
    private Usuario estudiante;

    //Para que el admin?
    // @ManyToOne
    // @JoinColumn(name = "administrador_id")
    // private Administrador administrador;

    //mismo cuento, boolean o tabla de estados
    @Column(name = "estado")
    private String estado;





}
