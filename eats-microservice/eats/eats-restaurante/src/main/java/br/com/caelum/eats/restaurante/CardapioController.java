package br.com.caelum.eats.restaurante;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.exception.ResourceNotFoundException;

@RestController
public class CardapioController {

	private CardapioRepository repo;

	public CardapioController(CardapioRepository cardapioRepo) {
		this.repo = cardapioRepo;
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio")
	public CardapioDto cardapioDoRestaurante(@PathVariable("idRestaurante") Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		Cardapio cardapio = repo.findByRestaurante(restaurante);
		return new CardapioDto(cardapio);
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}")
	public CardapioDto porId(@PathVariable("idCardapio") Long idCardapio) {
		Cardapio cardapio = repo.findById(idCardapio).orElseThrow(() -> new ResourceNotFoundException());
		return new CardapioDto(cardapio);
	}

}
