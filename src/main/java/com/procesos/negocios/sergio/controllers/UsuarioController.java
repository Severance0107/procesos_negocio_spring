package com.procesos.negocios.sergio.controllers;

import com.procesos.negocios.sergio.models.Usuario;
import com.procesos.negocios.sergio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario;
    }

    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuario/{nombre}/{apellido}")
    public List<Usuario> listarPorNombreApellido(@PathVariable String nombre, @PathVariable String apellido){

        return usuarioRepository.findAllByNombreAndApellido(nombre, apellido);

    }

    @GetMapping("/usuario/apellido/{apellido}")
    public List<Usuario> listarPorApellido(@PathVariable String apellido){
        return usuarioRepository.findAllByApellido(apellido);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public List<Usuario> listarPorNombre(@PathVariable String nombre){
        return usuarioRepository.findAllByNombre(nombre);
    }

    @PutMapping("/usuario/{id}")
    public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario usuarioBD = usuarioRepository.findById(id).get();
        try{
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellido(usuario.getApellido());
            usuarioBD.setDireccion(usuario.getDireccion());
            usuarioBD.setDocumento(usuario.getDocumento());
            usuarioBD.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioBD.setTelefono(usuario.getTelefono());
            usuarioRepository.save(usuarioBD);
            return usuarioBD;
        }catch (Exception e){
            return null;
        }
    }

    @DeleteMapping("usuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id){
        Usuario usuarioBD = usuarioRepository.findById(id).get();

        try {
            usuarioRepository.delete(usuarioBD);
            return usuarioBD;
        }catch (Exception e){
            return null;
        }

    }
}
