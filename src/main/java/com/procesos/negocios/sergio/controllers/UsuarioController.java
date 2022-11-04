package com.procesos.negocios.sergio.controllers;

import com.procesos.negocios.sergio.models.Usuario;
import com.procesos.negocios.sergio.repository.UsuarioRepository;
import com.procesos.negocios.sergio.services.UsuarioService;
import com.procesos.negocios.sergio.services.UsuarioServiceImpl;
import com.procesos.negocios.sergio.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.getUserById(id);
    }

    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioService.createUser(usuario);
    }

    @GetMapping("/usuarios")
    public ResponseEntity listarUsuarios(@RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.allUsers();
    }

    @GetMapping("/usuario/{nombre}/{apellido}")
    public ResponseEntity listarPorNombreApellido(@PathVariable String nombre, @PathVariable String apellido, @RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.allUserNameAndLastName(nombre, apellido);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable String nombre, @RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.allUserName(nombre);
    }

    @GetMapping("/usuario/apellido/{apellido}")
    public ResponseEntity listarPorApellido(@PathVariable String apellido, @RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.allUserLastName(apellido);
    }


    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@Valid @PathVariable Long id, @RequestBody Usuario usuario, @RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.editUser(id, usuario);
    }

    @DeleteMapping("usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id, @RequestHeader(value = "Authorization") String token){
        if (jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return usuarioService.deleteUserById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
