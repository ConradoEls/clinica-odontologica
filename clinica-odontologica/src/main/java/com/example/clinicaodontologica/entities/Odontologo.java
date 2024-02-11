 package com.example.clinicaodontologica.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "odontologos")
@Getter
@Setter
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String matricula;
    @Column
    private String nombre;
    @Column
    private String apellido;

    @OneToMany(mappedBy = "odontologo",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos= new HashSet<>();
}
