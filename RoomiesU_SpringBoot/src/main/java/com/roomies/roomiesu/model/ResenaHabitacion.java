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
@Table(name = "resena_habitacion")
public class ResenaHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creacion")
    private LocalDate fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    //Para que el admin?
    // @ManyToOne
    // @JoinColumn(name = "administrador_id")
    // private Administrador administrador;

    //Deberia tener fk de habitacion, no?
    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    //las calificaciones son de 1 a 5, o cómo?
    @Column(name = "calificacion")
    private Integer calificacion;

    //mismo cuento, boolean o tabla de estados
    @Column(name = "estado")
    private String estado;

}
