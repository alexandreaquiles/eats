package br.com.caelum.eats.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.CardapioSemRestaurante;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.Cardapio;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.CardapioRepository;

@RestController
public class CardapioController {

	private CardapioRepository repo;

	public CardapioController(CardapioRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio")
	public CardapioSemRestaurante cardapioDoRestaurante(@PathVariable("idRestaurante") Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		Cardapio cardapio = repo.findByRestaurante(restaurante);
		return new CardapioSemRestaurante(cardapio);
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}")
	public CardapioSemRestaurante porId(@PathVariable("idCardapio") Long idCardapio) {
		Cardapio cardapio = repo.findById(idCardapio).orElseThrow(() -> new ResourceNotFoundException());
		return new CardapioSemRestaurante(cardapio);
	}
}
