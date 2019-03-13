package br.com.caelum.eats.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.RestauranteSemHorariosDeFuncionamento;
import br.com.caelum.eats.repository.RestauranteRepository;

@RestController
@RequestMapping("/admin/restaurantes")
public class AdminRestauranteController {

	private RestauranteRepository repo;

	public AdminRestauranteController(RestauranteRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/em-aprovacao")
	public List<RestauranteSemHorariosDeFuncionamento> emAprovacao() {
		return repo.findAllByAprovado(false)
				.stream()
				.map(r -> new RestauranteSemHorariosDeFuncionamento(r))
				.collect(Collectors.toList());
	}

	@PatchMapping("/{id}")
	public void aprova(@PathVariable("id") Long id) {
		repo.aprovaPorId(id);
	}
}
