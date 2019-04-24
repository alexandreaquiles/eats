package br.com.caelum.apigateway;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestauranteComDistanciaController {

	private RestauranteRestClient restauranteRestClient;
	private DistanciaRestClient distanciaRestClient;

	public RestauranteComDistanciaController(RestauranteRestClient restauranteRestClient,
			DistanciaRestClient distanciaRestClient) {
		this.restauranteRestClient = restauranteRestClient;
		this.distanciaRestClient = distanciaRestClient;
	}

	@CrossOrigin
	@GetMapping("/restaurantes-com-distancia/{cep}/restaurante/{restauranteId}")
	public RestauranteComDistanciaDto porCepEIdComDistancia(@PathVariable("cep") String cep, @PathVariable("restauranteId") Long restauranteId) {
		RestauranteDto restaurante = restauranteRestClient.porId(restauranteId);
		RestauranteComDistanciaDto restauranteComDistancia = distanciaRestClient.porCepEId(cep, restauranteId);
		restauranteComDistancia.setRestaurante(restaurante);
		return restauranteComDistancia;
	}

}
