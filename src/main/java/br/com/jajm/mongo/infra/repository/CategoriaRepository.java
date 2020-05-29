package br.com.jajm.mongo.infra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.jajm.mongo.domain.model.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {

}
