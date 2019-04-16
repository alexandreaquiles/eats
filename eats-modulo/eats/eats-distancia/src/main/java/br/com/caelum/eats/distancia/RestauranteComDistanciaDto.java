package br.com.caelum.eats.distancia;

import java.math.BigDecimal;

import br.com.caelum.eats.restaurante.Restaurante;
import br.com.caelum.eats.restaurante.RestauranteDto;
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