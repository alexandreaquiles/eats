package br.com.caelum.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class DistanciaRestClient {

	private String distanciaServiceUrl;
	private RestTemplate restTemplate;

	public DistanciaRestClient(RestTemplate restTemplate, @Value("${configuracao.distancia.service.url}") String distanciaServiceUrl) {
		this.distanciaServiceUrl = distanciaServiceUrl;
		this.restTemplate = restTemplate;
	}

	@HystrixCommand(fallbackMethod="restauranteComDistanciaEmCache")
	public RestauranteComDistanciaDto porCepEId(String cep, Long restauranteId) {
		String url = distanciaServiceUrl+"/restaurantes/"+cep+"/restaurante/"+restauranteId;
		return restTemplate.getForObject(url, RestauranteComDistanciaDto.class);
	}

	public RestauranteComDistanciaDto restauranteComDistanciaEmCache(String cep, Long restauranteId) {
		return new RestauranteComDistanciaDto(restauranteId, null, null);
	}
}