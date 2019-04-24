package br.com.caelum.apigateway;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestauranteDto {

	private Long id;

	private String nome;

	private String descricao;

	private String cep;

	private String endereco;

	private BigDecimal taxaDeEntregaEmReais;

	private Integer tempoDeEntregaMinimoEmMinutos;

	private Integer tempoDeEntregaMaximoEmMinutos;

	private TipoDeCozinhaDto tipoDeCozinha;

}
