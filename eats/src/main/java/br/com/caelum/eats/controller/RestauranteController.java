package br.com.caelum.eats.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.RestauranteComDistancia;
import br.com.caelum.eats.dto.RestauranteComDistanciaEComMediaDeAvaliacoes;
import br.com.caelum.eats.dto.RestauranteSemFormasDePagamentoNemHorariosDeFuncionamento;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.AvaliacoesRepository;
import br.com.caelum.eats.repository.RestauranteRepository;
import br.com.caelum.eats.service.DistanciaService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	private RestauranteRepository restauranteRepo;
	private AvaliacoesRepository avaliacoesRepo;
	private DistanciaService distanciaService;

	public RestauranteController(RestauranteRepository restauranteRepo, AvaliacoesRepository avaliacoesRepo, DistanciaService repo) {
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

	@GetMapping("/{cep}/restaurante/{id}")
	public RestauranteComDistanciaEComMediaDeAvaliacoes comDistanciaEMediaDeAvaliacoesPorId(@PathVariable("cep") String cep, @PathVariable("id") Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		BigDecimal distancia = distanciaService.distanciaDoCep(restaurante, cep);
		RestauranteComDistancia restauranteComDistancia = new RestauranteComDistancia(restaurante, distancia);
		Double mediaAvaliacoes = avaliacoesRepo.mediaDoRestaurantePeloId(id);
		return new RestauranteComDistanciaEComMediaDeAvaliacoes(restauranteComDistancia, mediaAvaliacoes);
	}

	@GetMapping("/{id}")
	private RestauranteSemFormasDePagamentoNemHorariosDeFuncionamento detalha(@PathVariable("id") Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new RestauranteSemFormasDePagamentoNemHorariosDeFuncionamento(restaurante);
	}

	@PostMapping
	private Restaurante adiciona(@RequestBody Restaurante restaurante) {
		return restauranteRepo.save(restaurante);
	}

	@PutMapping("/{id}")
	private Restaurante atualiza(@RequestBody Restaurante restaurante) {
		return restauranteRepo.save(restaurante);
	}

}
