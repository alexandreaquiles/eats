package br.com.caelum.eats.restaurante;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
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

	public ClienteDistanciaService(RestTemplateBuilder restTemplateBuilder, @Value("${configuracao.distancia.service.url}") String distanciaServiceUrl) {
		this.distanciaServiceUrl = distanciaServiceUrl;
		this.restTemplate = restTemplateBuilder.build();
	}

	public void novoRestauranteAprovado(Restaurante restaurante) {
		RestauranteParaDistanciaService restauranteParaDistancia = new RestauranteParaDistanciaService(restaurante);
		String url = distanciaServiceUrl+"/restaurantes";
		System.out.println(url);
		restTemplate.postForObject(url, restauranteParaDistancia, RestauranteParaDistanciaService.class);
	}

	public void restauranteAtualizado(Restaurante restaurante) {
		RestauranteParaDistanciaService restauranteParaDistancia = new RestauranteParaDistanciaService(restaurante);
		restTemplate.put(distanciaServiceUrl+"/restaurantes/" + restaurante.getId(), restauranteParaDistancia, RestauranteParaDistanciaService.class);
	}
}
