package br.com.caelum.eats.dto;

import java.math.BigDecimal;

import br.com.caelum.eats.model.TipoDeCozinha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteMaisProximo {

	private Long id;
	
	private String nome;
	
	private TipoDeCozinha tipoDeCozinha;
	
	private BigDecimal taxaDeEntregaEmReais;
	
	private Integer tempoDeEntregaMinimoEmMinutos;
	
	private Integer tempoDeEntregaMaximoEmMinutos;

	private BigDecimal distancia;
	
}
