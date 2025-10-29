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
	
	@GetMapping(value = "/usuario/listar")
	public ResponseEntity<?> listarUsuarios() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Integer id) {
		if(usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso"); //204
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id não existe"); //404
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody Usuario user) {
		if(usuarioRepository.existsById(id)) {
			Usuario usuarioAtualizado = usuarioRepository.findById(id).get();
			usuarioAtualizado.setNome(user.getNome());
			usuarioAtualizado.setEmail(user.getEmail());
			usuarioAtualizado.setPassword(user.getPassword());
			usuarioRepository.save(usuarioAtualizado);
			return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado); //200
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id não existe"); //404
		}
	}

}
