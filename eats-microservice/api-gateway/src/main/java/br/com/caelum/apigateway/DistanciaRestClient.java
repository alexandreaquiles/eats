package br.com.caelum.apigateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("distancia")
public interface DistanciaRestClient {

	@GetMapping("/restaurantes/{cep}/restaurante/{restauranteId}")
	public RestauranteComDistanciaDto porCepEId(@PathVariable("cep") String cep, @PathVariable("restauranteId") Long restauranteId);

}