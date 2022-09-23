package com.procesos.negocios.sergio.controllers;

import com.procesos.negocios.sergio.models.Usuario;
import com.procesos.negocios.sergio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Sergio");
        usuario.setApellido("Ortiz");
        usuario.setDocumento("191792");
        usuario.setDireccion("Calle 6E");
        usuario.setFechaNacimiento(new Date(2002/07/01));
        usuario.setTelefono("3016037779");
        return usuario;
    }

    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;
    }
}
