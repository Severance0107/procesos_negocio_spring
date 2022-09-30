package com.procesos.negocios.sergio.controllers;

import com.procesos.negocios.sergio.models.Usuario;
import com.procesos.negocios.sergio.repository.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            return new ResponseEntity(usuario, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@RequestBody Usuario usuario){
        try {
            usuarioRepository.save(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuario/{nombre}/{apellido}")
    public ResponseEntity listarPorNombreApellido(@PathVariable String nombre,
                                                 @PathVariable String apellido){
        List<Usuario> usuarios =usuarioRepository.findAllByNombreAndApellido(nombre, apellido);
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuario/apellido/{apellido}")
    public ResponseEntity listarPorApellido(@PathVariable String apellido){
        List<Usuario> usuarios = usuarioRepository.findAllByApellido(apellido);
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable String nombre){
        List<Usuario> usuarios =usuarioRepository.findAllByNombre(nombre);
        if (usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios, HttpStatus.OK);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id);
        if (usuarioBD.isPresent()) {
            try {
                usuarioBD.get().setNombre(usuario.getNombre());
                usuarioBD.get().setApellido(usuario.getApellido());
                usuarioBD.get().setDireccion(usuario.getDireccion());
                usuarioBD.get().setDocumento(usuario.getDocumento());
                usuarioBD.get().setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioBD.get().setTelefono(usuario.getTelefono());
                usuarioRepository.save(usuarioBD.get());
                return new ResponseEntity(usuario, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
            return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioBD = usuarioRepository.findById(id);
        if (usuarioBD.isPresent()){
            usuarioRepository.delete(usuarioBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
