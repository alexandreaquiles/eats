package br.com.caelum.eats.dto;

import java.math.BigDecimal;

import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.model.TipoDeCozinha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteComDistancia {

	private Long id;
	
	private String nome;

	private String descricao;

	private TipoDeCozinha tipoDeCozinha;
	
	private BigDecimal taxaDeEntregaEmReais;
	
	private Integer tempoDeEntregaMinimoEmMinutos;
	
	private Integer tempoDeEntregaMaximoEmMinutos;

	private BigDecimal distancia;
	
	public RestauranteComDistancia(Restaurante restaurante, BigDecimal distancia) {
		this(restaurante.getId(), restaurante.getNome(), restaurante.getDescricao(), restaurante.getTipoDeCozinha(), restaurante.getTaxaDeEntregaEmReais(), restaurante.getTempoDeEntregaMinimoEmMinutos(), restaurante.getTempoDeEntregaMaximoEmMinutos(), distancia);
	}
	
}
