package br.com.caelum.eats.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.RestauranteSemHorariosDeFuncionamento;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.Cardapio;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.CardapioRepository;
import br.com.caelum.eats.repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	private RestauranteRepository restauranteRepo;
	private CardapioRepository cardapioRepo;

	public RestauranteController(RestauranteRepository restauranteRepo, CardapioRepository cardapioRepo) {
		this.restauranteRepo = restauranteRepo;
		this.cardapioRepo = cardapioRepo;
	}

	@GetMapping("/{id}")
	private RestauranteSemHorariosDeFuncionamento detalha(@PathVariable("id") Long id) {
		Restaurante restaurante = restauranteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new RestauranteSemHorariosDeFuncionamento(restaurante);
	}

	@PostMapping
	private Restaurante adiciona(@RequestBody Restaurante restaurante) {
		restaurante.setAprovado(false);
		Restaurante restauranteSalvo = restauranteRepo.save(restaurante);
		Cardapio cardapio = new Cardapio();
		cardapio.setRestaurante(restauranteSalvo);
		cardapioRepo.save(cardapio);
		return restauranteSalvo;
	}

	@PutMapping("/{id}")
	private Restaurante atualiza(@RequestBody Restaurante restaurante) {
		return restauranteRepo.save(restaurante);
	}

}
