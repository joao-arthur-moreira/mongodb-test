package br.com.jajm.mongo.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Pessoa {
	
	@Id
	private String codigo;
	
	private String nome;
	
	private Boolean status;
	
	private String cidade;
	private String estado;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

}
