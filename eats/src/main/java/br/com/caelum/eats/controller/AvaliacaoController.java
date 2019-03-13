package br.com.caelum.eats.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.AvaliacaoSemPedidoNemRestaurante;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.AvaliacoesRepository;

@RestController
public class AvaliacaoController {

	private AvaliacoesRepository repo;

	public AvaliacaoController(AvaliacoesRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/restaurantes/{restauranteId}/avaliacoes")
	public List<AvaliacaoSemPedidoNemRestaurante> listaDoRestaurante(@PathVariable("restauranteId") Long restauranteId) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(restauranteId);
		return repo.findAllByRestaurante(restaurante)
				.stream()
				.map(a -> new AvaliacaoSemPedidoNemRestaurante(a))
				.collect(Collectors.toList());
	}
}
