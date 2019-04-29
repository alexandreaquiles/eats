package br.com.caelum.eats.restaurante;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
public class ClienteDistanciaService {
	
	@Data
	@AllArgsConstructor
	private static class RestauranteParaDistanciaService {

		private Long id;
		private String cep;
		private Long tipoDeCozinhaId;

		private RestauranteParaDistanciaService(Restaurante restaurante){
			this(restaurante.getId(), restaurante.getCep(), restaurante.getTipoDeCozinha().getId());
		}
	}

	private String distanciaServiceUrl;
	private RestTemplate restTemplate;

	public ClienteDistanciaService(RestTemplate restTemplate, @Value("${configuracao.distancia.service.url}") String distanciaServiceUrl) {
		this.distanciaServiceUrl = distanciaServiceUrl;
		this.restTemplate = restTemplate;
	}

	public void novoRestauranteAprovado(Restaurante restaurante) {
		RestauranteParaDistanciaService restauranteParaDistancia = new RestauranteParaDistanciaService(restaurante);
		String url = distanciaServiceUrl+"/restaurantes";
		ResponseEntity<RestauranteParaDistanciaService> responseEntity = restTemplate.postForEntity(url, restauranteParaDistancia, RestauranteParaDistanciaService.class);
		HttpStatus statusCode = responseEntity.getStatusCode();
		if(!HttpStatus.CREATED.equals(statusCode)) {
			throw new RuntimeException("Status diferente do esperado: " + statusCode);
		}
	}

	public void restauranteAtualizado(Restaurante restaurante) {
		RestauranteParaDistanciaService restauranteParaDistancia = new RestauranteParaDistanciaService(restaurante);
		String url = distanciaServiceUrl+"/restaurantes/" + restaurante.getId();
		restTemplate.put(url, restauranteParaDistancia, RestauranteParaDistanciaService.class);
	}
}
