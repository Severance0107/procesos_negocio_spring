package com.procesos.negocios.sergio.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;
    @Column(length = 300, nullable = false)
    @NotBlank(message = "El apellido no puede estar en blanco")
    private String apellido;
    @Column(length = 20, nullable = false, unique= true)
    @NotBlank(message = "El documento no puede estar en blanco")
    private String documento;
    @Column(length = 100)
    private String direccion;
    private Date fechaNacimiento;
    @Column(length = 20)
    private String telefono;
    @Column(unique = true, length = 100, nullable = false)
    @NotBlank(message = "El correo no puede estar en blanco")
    private String correo;
    @Column(nullable = false, length = 64)
    @NotBlank(message = "La contraseña no puede estar en blanco")
    private String password;
}
