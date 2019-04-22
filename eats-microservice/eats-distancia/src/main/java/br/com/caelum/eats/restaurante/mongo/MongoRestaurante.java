package br.com.caelum.eats.restaurante.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document
@Data
@AllArgsConstructor
public class MongoRestaurante {

	private Long id;

	private String cep;

	private Long tipoDeCozinhaId;
}
