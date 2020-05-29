package br.com.jajm.mongo.infra.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.jajm.mongo.domain.model.Lancamento;

public interface LancamentoRepository extends MongoRepository<Lancamento, String> {
	
	List<Lancamento> findByDescricaoContainingAndDataVencimentoBetween(String descricao, LocalDate davencimentoDe, LocalDate dataVencimentoAte);

}
