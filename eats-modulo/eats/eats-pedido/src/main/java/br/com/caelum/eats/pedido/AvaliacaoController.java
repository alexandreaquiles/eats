package br.com.caelum.eats.pedido;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.restaurante.Restaurante;

@RestController
public class AvaliacaoController {

	private AvaliacaoRepository repo;

	public AvaliacaoController(AvaliacaoRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/restaurantes/{restauranteId}/avaliacoes")
	public List<AvaliacaoDto> listaDoRestaurante(@PathVariable("restauranteId") Long restauranteId) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(restauranteId);
		return repo.findAllByRestaurante(restaurante).stream().map(a -> new AvaliacaoDto(a))
				.collect(Collectors.toList());
	}

	@GetMapping("/restaurantes/{restauranteId}/media-avaliacoes")
	public Double mediaDasAvaliacoesDoRestaurante(@PathVariable("restauranteId") Long restauranteId) {
		return repo.mediaDoRestaurantePeloId(restauranteId);
	}

	@PostMapping("/restaurantes/{restauranteId}/avaliacoes")
	public AvaliacaoDto adiciona(@RequestBody Avaliacao avaliacao) {
		Avaliacao salvo = repo.save(avaliacao);
		return new AvaliacaoDto(salvo);
	}

}
