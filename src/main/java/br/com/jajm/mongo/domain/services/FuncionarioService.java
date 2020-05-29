package br.com.jajm.mongo.domain.services;

import java.util.List;

import br.com.jajm.mongo.domain.model.Funcionario;

public interface FuncionarioService {
	
	List<Funcionario> listarTodos();

	Funcionario criar(Funcionario funcionario);
	
	Funcionario buscar(String codigo);

}
