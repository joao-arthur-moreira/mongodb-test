package br.com.jajm.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jajm.mongo.domain.model.Funcionario;
import br.com.jajm.mongo.domain.services.FuncionarioService;

@RestController
@RequestMapping(path = "/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	public List<Funcionario> listarTodos() {
		return funcionarioService.listarTodos();
	}
	
	@PostMapping
	public Funcionario criar(@RequestBody Funcionario funcionario) {
		return funcionarioService.criar(funcionario);
	}
	
	@GetMapping("/{codigo}")
	public Funcionario buscar(@PathVariable String codigo) {
		return funcionarioService.buscar(codigo);
	}

}
