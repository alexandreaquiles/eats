package br.com.caelum.eats.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.RestauranteMaisProximo;
import br.com.caelum.eats.dto.RestauranteMaisProximoComMediaDeAvaliacoes;
import br.com.caelum.eats.repository.AvaliacoesRepository;
import br.com.caelum.eats.service.DistanciaService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	private DistanciaService distanciaService;
	private AvaliacoesRepository avaliacoesRepo;

	public RestauranteController(AvaliacoesRepository avaliacoesRepo, DistanciaService repo) {
		this.avaliacoesRepo = avaliacoesRepo;
		this.distanciaService = repo;
	}


	@GetMapping("/mais-proximos/{cep}")
	public List<RestauranteMaisProximoComMediaDeAvaliacoes> maisProximos(@PathVariable("cep") String cep) {
		List<RestauranteMaisProximo> maisProximosAoCep = distanciaService.restaurantesMaisProximosAoCep(cep);
		return maisProximosAoCep
			.stream()
			.map(restauranteMaisProximo -> {
				Double mediaAvaliacoes = avaliacoesRepo.mediaDoRestaurantePeloId(restauranteMaisProximo.getId());
				return new RestauranteMaisProximoComMediaDeAvaliacoes(restauranteMaisProximo, mediaAvaliacoes);
			})
			.collect(Collectors.toList());
	}

}
