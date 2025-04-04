package com.roomies.roomiesu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "password")
    private String password;

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

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RolModelo.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuario_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<RolModelo> roles;


}
