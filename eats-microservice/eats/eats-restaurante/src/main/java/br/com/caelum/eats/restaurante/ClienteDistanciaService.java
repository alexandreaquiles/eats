package br.com.caelum.eats.restaurante;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
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

	@Retryable(maxAttempts=5, backoff=@Backoff(delay=2000,multiplier=2))
	public void restauranteAtualizado(Restaurante restaurante) {
		System.out.println("\n\n");
		System.out.println("----- MONOLITO CHAMANDO DISTANCIA SERVICE -----------------");
		System.out.println(LocalDateTime.now());
		System.out.println("--------------------------------------------------\n\n");

		RestauranteParaDistanciaService restauranteParaDistancia = new RestauranteParaDistanciaService(restaurante);
		String url = distanciaServiceUrl+"/restaurantes/" + restaurante.getId();
		restTemplate.put(url, restauranteParaDistancia, RestauranteParaDistanciaService.class);
	}
}
