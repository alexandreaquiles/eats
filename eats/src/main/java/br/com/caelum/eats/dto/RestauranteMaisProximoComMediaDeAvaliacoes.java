package br.com.caelum.eats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Delegate;

@Data
@AllArgsConstructor
public class RestauranteMaisProximoComMediaDeAvaliacoes {

	@Delegate
	private RestauranteMaisProximo restauranteMaisProximo;
	
	private Double mediaAvaliacoes;

}
