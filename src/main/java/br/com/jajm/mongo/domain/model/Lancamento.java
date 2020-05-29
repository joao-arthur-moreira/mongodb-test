package br.com.jajm.mongo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Lancamento {
	
	@Id
	private String codigo;	
	
	private Tipo tipo;
	
	private String descricao;
	
	private LocalDate dataVencimento;
	
	private LocalDate dataPagamento;
	
	private BigDecimal valor;
	
	@DBRef
	private Pessoa pessoa;
	
	@DBRef
	private Categoria categoria;

}
