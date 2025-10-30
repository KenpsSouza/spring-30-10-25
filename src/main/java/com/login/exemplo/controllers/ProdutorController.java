package com.login.exemplo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.exemplo.entity.Produtor;
import com.login.exemplo.repositories.ProdutorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtor")
public class ProdutorController {

	@Autowired
	ProdutorRepository produtorRepository;

	@GetMapping("/{id}")
	public ResponseEntity<Produtor> buscarId(@PathVariable int id) {
		Optional<Produtor> produtor = produtorRepository.findById(id);
		return produtor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public List<Produtor> buscarTodos() {
		return produtorRepository.findAll();
	}

	@PostMapping
	public Produtor criarProdutor(@RequestBody Produtor produtor) {
		return produtorRepository.save(produtor);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produtor> atualizarProdutor(@PathVariable int id, @RequestBody Produtor produtorAtualizado) {
		Optional<Produtor> produtorOptional = produtorRepository.findById(id);
		if (produtorOptional.isPresent()) {
			Produtor produtor = produtorOptional.get();
			produtor.setNome(produtorAtualizado.getNome());
			produtor.setEndereco(produtorAtualizado.getEndereco());
			produtorRepository.save(produtor);
			return ResponseEntity.ok(produtor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProdutor(@PathVariable int id) {
		if (produtorRepository.existsById(id)) {
			produtorRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}