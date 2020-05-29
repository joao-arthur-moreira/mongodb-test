package br.com.jajm.mongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.jajm.mongo.domain.model.Categoria;
import br.com.jajm.mongo.domain.model.Lancamento;
import br.com.jajm.mongo.domain.model.Pessoa;
import br.com.jajm.mongo.domain.model.filtros.FiltroLancamento;
import br.com.jajm.mongo.infra.repository.CategoriaRepository;
import br.com.jajm.mongo.infra.repository.LancamentoRepository;
import br.com.jajm.mongo.infra.repository.PessoaRepository;

@RestController
@RequestMapping(path = "/lancamentos")
@CrossOrigin
public class LancamentoController {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public Page<Lancamento> listarTodos(Pageable pageable) {
		return lancamentoRepository.findAll(pageable);
	}
	
	@GetMapping("/resumo")
	public List<Lancamento> filtrar(FiltroLancamento filtroLancamento) {
		return lancamentoRepository.findByDescricaoContainingAndDataVencimentoBetween(filtroLancamento.getDescricao(), 
				filtroLancamento.getDataVencimentoDe(), filtroLancamento.getDataVencimentoAte());
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> porCodigo(@PathVariable String codigo) {
		Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(codigo);
		return lancamentoOptional.isPresent() ? 
				ResponseEntity
				.ok(lancamentoOptional.get()) : 
			ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus( value = HttpStatus.CREATED )
	public Lancamento adicionar(@RequestBody Lancamento lancamento) {
		Optional<Pessoa> pessoaOptional = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(lancamento.getCategoria().getCodigo());
		
		lancamento.setPessoa(pessoaOptional.get());
		lancamento.setCategoria(categoriaOptional.get());
		
		return lancamentoRepository.save(lancamento);
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> remover(@PathVariable String codigo) {
		Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(codigo);
		if (lancamentoOptional.isPresent()) {			
			lancamentoRepository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping
	public Lancamento atualizar(@RequestBody Lancamento lancamento) {
		return lancamentoRepository.save(lancamento);
	}

}
