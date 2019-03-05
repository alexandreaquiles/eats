package br.com.caelum.eats.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.model.Cardapio;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.CardapioRepository;

@RestController
public class CardapioController {

	private CardapioRepository repo;
	
	public CardapioController(CardapioRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/restaurantes/{id}/cardapio")
	public Cardapio cardapioDoRestaurante (@PathVariable("id") Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		return repo.findByRestaurante(restaurante);
	}

}
