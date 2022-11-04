package com.procesos.negocios.sergio.repository;

import com.procesos.negocios.sergio.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findAllByNombre(String nombre);
    List<Usuario> findAllByNombreAndApellido(String nombre, String apellido);
    List<Usuario> findAllByApellido(String apellido);
    Usuario findByCorreo(String correo);
}
