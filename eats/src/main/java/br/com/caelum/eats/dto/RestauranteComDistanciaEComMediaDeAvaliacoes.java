package br.com.caelum.eats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Delegate;

@Data
@AllArgsConstructor
public class RestauranteComDistanciaEComMediaDeAvaliacoes {

	@Delegate
	private RestauranteComDistancia restauranteComDistancia;
	
	private Double mediaAvaliacoes;

}
