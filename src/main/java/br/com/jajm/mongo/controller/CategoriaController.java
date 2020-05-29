package br.com.jajm.mongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jajm.mongo.domain.model.Categoria;
import br.com.jajm.mongo.infra.repository.CategoriaRepository;

@RestController
@RequestMapping(path = "/categorias")
@CrossOrigin
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> listarTodas() {
		return categoriaRepository.findAll();
	}	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> porCodigo(@PathVariable String codigo) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(codigo);
		return categoriaOptional.isPresent() ? 
				ResponseEntity
				.ok(categoriaOptional.get()) : 
			ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Categoria adicionar(@RequestBody Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	@DeleteMapping("/{codigo}")	
	public ResponseEntity<?> remover(@PathVariable String codigo) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(codigo);
		if (categoriaOptional.isPresent()) {			
			categoriaRepository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
