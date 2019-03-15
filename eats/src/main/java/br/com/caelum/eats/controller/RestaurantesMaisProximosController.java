package br.com.caelum.eats.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.RestauranteComDistancia;
import br.com.caelum.eats.dto.RestauranteComDistanciaEComMediaDeAvaliacoes;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.AvaliacaoRepository;
import br.com.caelum.eats.repository.RestauranteRepository;
import br.com.caelum.eats.service.DistanciaService;

@RestController
@RequestMapping("/restaurantes")
public class RestaurantesMaisProximosController {

	private RestauranteRepository restauranteRepo;
	private AvaliacaoRepository avaliacoesRepo;
	private DistanciaService distanciaService;

	public RestaurantesMaisProximosController(RestauranteRepository restauranteRepo, AvaliacaoRepository avaliacoesRepo, DistanciaService repo) {
		this.restauranteRepo = restauranteRepo;
		this.avaliacoesRepo = avaliacoesRepo;
		this.distanciaService = repo;
	}

	@GetMapping("/mais-proximos/{cep}")
	public List<RestauranteComDistanciaEComMediaDeAvaliacoes> maisProximos(@PathVariable("cep") String cep) {
		List<RestauranteComDistancia> maisProximosAoCep = distanciaService.restaurantesMaisProximosAoCep(cep);
		return maisProximosAoCep
			.stream()
			.map(restauranteMaisProximo -> {
				Double mediaAvaliacoes = avaliacoesRepo.mediaDoRestaurantePeloId(restauranteMaisProximo.getId());
				return new RestauranteComDistanciaEComMediaDeAvaliacoes(restauranteMaisProximo, mediaAvaliacoes);
			})
			.collect(Collectors.toList());
	}

	@GetMapping("/mais-proximos/{cep}/tipos-de-cozinha/{tipoDeCozinhaId}")
	public List<RestauranteComDistanciaEComMediaDeAvaliacoes> maisProximos(@PathVariable("cep") String cep, @PathVariable("tipoDeCozinhaId") Long tipoDeCozinhaId) {
		List<RestauranteComDistancia> maisProximosAoCep = distanciaService.restaurantesDoTipoDeCozinhaMaisProximosAoCep(tipoDeCozinhaId, cep);
		return maisProximosAoCep
			.stream()
			.map(restauranteMaisProximo -> {
				Double mediaAvaliacoes = avaliacoesRepo.mediaDoRestaurantePeloId(restauranteMaisProximo.getId());
				return new RestauranteComDistanciaEComMediaDeAvaliacoes(restauranteMaisProximo, mediaAvaliacoes);
			})
			.collect(Collectors.toList());
	}

	@GetMapping("/{cep}/restaurante/{id}")
	public RestauranteComDistanciaEComMediaDeAvaliacoes comDistanciaEMediaDeAvaliacoesPorId(@PathVariable("cep") String cep, @PathVariable("id") Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		BigDecimal distancia = distanciaService.distanciaDoCep(restaurante, cep);
		RestauranteComDistancia restauranteComDistancia = new RestauranteComDistancia(restaurante, distancia);
		Double mediaAvaliacoes = avaliacoesRepo.mediaDoRestaurantePeloId(id);
		return new RestauranteComDistanciaEComMediaDeAvaliacoes(restauranteComDistancia, mediaAvaliacoes);
	}

}
