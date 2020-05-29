package br.com.jajm.mongo.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Categoria {
	
	@Id
	private String codigo;
	
	private String nome;

}
