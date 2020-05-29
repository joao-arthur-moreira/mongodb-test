package br.com.jajm.mongo.infra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.jajm.mongo.domain.model.Funcionario;

public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {

}
