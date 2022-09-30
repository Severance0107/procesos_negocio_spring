package com.procesos.negocios.sergio.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 300, nullable = false)
    private String apellido;
    @Column(length = 20, nullable = false)
    private String documento;
    @Column(length = 100)
    private String direccion;
    private Date fechaNacimiento;
    @Column(length = 20)
    private String telefono;

}
