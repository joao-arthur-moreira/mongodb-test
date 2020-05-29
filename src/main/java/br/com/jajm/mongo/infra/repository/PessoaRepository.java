package br.com.jajm.mongo.infra.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.jajm.mongo.domain.model.Pessoa;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {
	
	List<Pessoa> findByNomeContainingIgnoreCase(String nome);

}
