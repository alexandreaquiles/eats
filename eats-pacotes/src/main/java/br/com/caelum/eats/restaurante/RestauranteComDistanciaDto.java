package br.com.caelum.eats.restaurante;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteComDistanciaDto {

	@Delegate
	private RestauranteDto restaurante;

	private BigDecimal distancia;
	
	public RestauranteComDistanciaDto(Restaurante restaurante, BigDecimal distancia) {
		this(new RestauranteDto(restaurante), distancia);
	}

}
