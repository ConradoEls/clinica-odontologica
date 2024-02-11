package com.example.clinicaodontologica.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "pacientes")
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column(unique = true, nullable = false)
    private String documento;
    @Column
    private LocalDate fechaIngreso;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "paciente",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos= new HashSet<>();
}
