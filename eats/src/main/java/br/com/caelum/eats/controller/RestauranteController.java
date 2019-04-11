package br.com.caelum.eats.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.RestauranteComDistancia;
import br.com.caelum.eats.dto.RestauranteComDistanciaEComMediaDeAvaliacoes;
import br.com.caelum.eats.dto.RestauranteDto;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.Cardapio;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.AvaliacaoRepository;
import br.com.caelum.eats.repository.CardapioRepository;
import br.com.caelum.eats.repository.RestauranteRepository;
import br.com.caelum.eats.service.DistanciaService;

@RestController
public class RestauranteController {

	private RestauranteRepository restauranteRepo;
	private CardapioRepository cardapioRepo;
	private AvaliacaoRepository avaliacoesRepo;
	private DistanciaService distanciaService;

	public RestauranteController(RestauranteRepository restauranteRepo, CardapioRepository cardapioRepo,
			AvaliacaoRepository avaliacoesRepo, DistanciaService distanciaService) {
		this.restauranteRepo = restauranteRepo;
		this.cardapioRepo = cardapioRepo;
		this.avaliacoesRepo = avaliacoesRepo;
		this.distanciaService = distanciaService;
	}

	@GetMapping("/restaurantes/{id}")
	private RestauranteDto detalha(@PathVariable("id") Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new RestauranteDto(restaurante);
	}

	@GetMapping("/restaurantes/mais-proximos/{cep}")
	public List<RestauranteComDistanciaEComMediaDeAvaliacoes> maisProximos(@PathVariable("cep") String cep) {
		List<RestauranteComDistancia> maisProximosAoCep = distanciaService.restaurantesMaisProximosAoCep(cep);
		return maisProximosAoCep.stream().map(restauranteMaisProximo -> {
			Double mediaAvaliacoes = avaliacoesRepo.mediaDoRestaurantePeloId(restauranteMaisProximo.getId());
			return new RestauranteComDistanciaEComMediaDeAvaliacoes(restauranteMaisProximo, mediaAvaliacoes);
		}).collect(Collectors.toList());
	}

	@GetMapping("/restaurantes/mais-proximos/{cep}/tipos-de-cozinha/{tipoDeCozinhaId}")
	public List<RestauranteComDistanciaEComMediaDeAvaliacoes> maisProximos(@PathVariable("cep") String cep,
			@PathVariable("tipoDeCozinhaId") Long tipoDeCozinhaId) {
		List<RestauranteComDistancia> maisProximosAoCep = distanciaService
				.restaurantesDoTipoDeCozinhaMaisProximosAoCep(tipoDeCozinhaId, cep);
		return maisProximosAoCep.stream().map(restauranteMaisProximo -> {
			Double mediaAvaliacoes = avaliacoesRepo.mediaDoRestaurantePeloId(restauranteMaisProximo.getId());
			return new RestauranteComDistanciaEComMediaDeAvaliacoes(restauranteMaisProximo, mediaAvaliacoes);
		}).collect(Collectors.toList());
	}

	@GetMapping("/restaurantes/{cep}/restaurante/{id}")
	public RestauranteComDistanciaEComMediaDeAvaliacoes comDistanciaEMediaDeAvaliacoesPorId(
			@PathVariable("cep") String cep, @PathVariable("id") Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		BigDecimal distancia = distanciaService.distanciaDoCep(restaurante, cep);
		RestauranteComDistancia restauranteComDistancia = new RestauranteComDistancia(restaurante, distancia);
		Double mediaAvaliacoes = avaliacoesRepo.mediaDoRestaurantePeloId(id);
		return new RestauranteComDistanciaEComMediaDeAvaliacoes(restauranteComDistancia, mediaAvaliacoes);
	}
	
	@GetMapping("/parceiros/restaurantes/{id}")
	private RestauranteDto detalhaParceiro(@PathVariable("id") Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new RestauranteDto(restaurante);
	}

	@PostMapping("/parceiros/restaurantes")
	private Restaurante adiciona(@RequestBody Restaurante restaurante) {
		restaurante.setAprovado(false);
		Restaurante restauranteSalvo = restauranteRepo.save(restaurante);
		Cardapio cardapio = new Cardapio();
		cardapio.setRestaurante(restauranteSalvo);
		cardapioRepo.save(cardapio);
		return restauranteSalvo;
	}

	@PutMapping("/parceiros/restaurantes/{id}")
	private Restaurante atualiza(@RequestBody Restaurante restaurante) {
		Restaurante doBD = restauranteRepo.getOne(restaurante.getId());
		restaurante.setUser(doBD.getUser());
		restaurante.setAprovado(doBD.getAprovado());
		return restauranteRepo.save(restaurante);
	}

	@GetMapping("/admin/restaurantes/em-aprovacao")
	public List<RestauranteDto> emAprovacao() {
		return restauranteRepo.findAllByAprovado(false).stream().map(r -> new RestauranteDto(r))
				.collect(Collectors.toList());
	}

	@PatchMapping("/admin/restaurantes/{id}")
	public void aprova(@PathVariable("id") Long id) {
		restauranteRepo.aprovaPorId(id);
	}
}
