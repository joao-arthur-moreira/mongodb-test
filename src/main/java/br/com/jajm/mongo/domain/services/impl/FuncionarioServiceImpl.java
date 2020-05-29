package br.com.jajm.mongo.domain.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jajm.mongo.domain.model.Funcionario;
import br.com.jajm.mongo.domain.services.FuncionarioService;
import br.com.jajm.mongo.infra.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funrionarioRepository;

	@Override
	public List<Funcionario> listarTodos() {		
		return funrionarioRepository.findAll();
	}

	@Override
	public Funcionario criar(Funcionario funcionario) {
		Funcionario chefe = funrionarioRepository.findById(funcionario.getChefe().getCodigo()).get();
		funcionario.setChefe(chefe);
		
		return funrionarioRepository.save(funcionario);
	}

	@Override
	public Funcionario buscar(String codigo) {
		return funrionarioRepository.findById(codigo).get();
	}

}
