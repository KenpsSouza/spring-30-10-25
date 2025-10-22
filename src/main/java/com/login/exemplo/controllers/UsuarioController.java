package com.login.exemplo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repositories.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping(value = "usuario/cadastro")
	public ResponseEntity <Usuario> saveUser(@RequestBody Usuario user) {
		Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getPassword());
		usuarioRepository.save(usuario);
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping(value = "login")
	public ResponseEntity <?> login(@RequestBody Usuario user) {	
		
		Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
		if (findUser == null) {
			return ResponseEntity.ok("Usuário não encontrado");
		}else {
			if (findUser.getPassword().equals(user.getPassword())) {
				return ResponseEntity.ok("Login realizado com sucesso");
		} else {
				return ResponseEntity.ok("Senha incorreta");
			}
		}
	}
	
	

}
