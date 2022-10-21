package com.procesos.negocios.sergio.controllers;

import com.procesos.negocios.sergio.models.Usuario;
import com.procesos.negocios.sergio.repository.UsuarioRepository;
import com.procesos.negocios.sergio.services.UsuarioService;
import com.procesos.negocios.sergio.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
        return usuarioService.getUserById(id);
    }

    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.createUser(usuario);
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios(){
        return usuarioService.allUsers();
    }

    @GetMapping("/usuario/{nombre}/{apellido}")
    public ResponseEntity listarPorNombreApellido(@PathVariable String nombre, @PathVariable String apellido){
        return usuarioService.allUserNameAndLastName(nombre, apellido);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable String nombre){
        return usuarioService.allUserName(nombre);
    }

    @GetMapping("/usuario/apellido/{apellido}")
    public ResponseEntity listarPorApellido(@PathVariable String apellido){
        return usuarioService.allUserLastName(apellido);
    }


    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.editUser(id, usuario);
    }

    @DeleteMapping("usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        return usuarioService.deleteUserById(id);
    }
}
