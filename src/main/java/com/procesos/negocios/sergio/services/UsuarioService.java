package com.procesos.negocios.sergio.services;

import com.procesos.negocios.sergio.models.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {
    ResponseEntity<Usuario> getUserById(Long id);
    ResponseEntity<Usuario> createUser(Usuario usuario);
    ResponseEntity<List<Usuario>> allUsers();
    ResponseEntity<List<Usuario>> allUserNameAndLastName(String nombre, String apellido);
    ResponseEntity<List<Usuario>> allUserName(String nombre);
    ResponseEntity<List<Usuario>> allUserLastName(String apellido);
    ResponseEntity<Usuario> editUser(Long id, Usuario usuario);
    ResponseEntity<Usuario> deleteUserById(Long id);

    ResponseEntity login(String correo, String password);
}
