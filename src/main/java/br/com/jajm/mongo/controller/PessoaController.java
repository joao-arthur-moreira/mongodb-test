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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.jajm.mongo.domain.model.Pessoa;
import br.com.jajm.mongo.infra.repository.PessoaRepository;

@RestController
@RequestMapping(path = "/pessoas")
@CrossOrigin
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa> listarTodas() {
		return pessoaRepository.findAll();
	}
	
	@GetMapping("/por-nome")
	public List<Pessoa> listarPorNome(String nome) {
		return pessoaRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> porCodigo(@PathVariable String codigo) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(codigo);
		return pessoaOptional.isPresent() ? 
				ResponseEntity
				.ok(pessoaOptional.get()) : 
			ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@DeleteMapping("/{codigo}")	
	public ResponseEntity<?> remover(@PathVariable String codigo) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(codigo);
		if (pessoaOptional.isPresent()) {			
			pessoaRepository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping
	public Pessoa atualizar(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@PutMapping("/{codigo}/ativar")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable String codigo) {
		ativarInativar(codigo, true);
	}
	
	@PutMapping("/{codigo}/inativar")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable String codigo) {
		ativarInativar(codigo, false);
	}

	private void ativarInativar(String codigo, boolean status) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(codigo);
		Pessoa pessoa = pessoaOptional.get();
		pessoa.setStatus(status);
		
		pessoaRepository.save(pessoa);
	}

}
