package com.login.exemplo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.entity.Usuario;
import com.login.exemploService.UsuarioService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(value = "usuario/cadastro")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UsuarioRequestDTO user) {
        try {
            UsuarioResponseDTO saved = usuarioService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody UsuarioRequestDTO user) {
        String result = usuarioService.login(user);
        if ("Login realizado com sucesso".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    @GetMapping(value = "usuarios")
    public ResponseEntity<?> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        String result = usuarioService.deletar1(id);
        if ("Excluido com sucesso".equals(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody UsuarioRequestDTO user) {
        try {
            Usuario updated = usuarioService.atualizar(id, user);
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}