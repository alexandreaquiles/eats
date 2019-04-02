package br.com.caelum.eats.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.CardapioDto;
import br.com.caelum.eats.dto.CategoriaDoCardapioDto;
import br.com.caelum.eats.dto.ItemDoCardapioDto;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.Cardapio;
import br.com.caelum.eats.model.CategoriaDoCardapio;
import br.com.caelum.eats.model.ItemDoCardapio;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.CardapioRepository;
import br.com.caelum.eats.repository.CategoriaDoCardapioRepository;
import br.com.caelum.eats.repository.ItemDoCardapioRepository;

@RestController
public class CardapioController {

	private CardapioRepository cardapioRepo;
	private CategoriaDoCardapioRepository categoriaRepo;
	private ItemDoCardapioRepository itemRepo;

	public CardapioController(CardapioRepository cardapioRepo, CategoriaDoCardapioRepository categoriaRepo, ItemDoCardapioRepository itemRepo) {
		this.cardapioRepo = cardapioRepo;
		this.categoriaRepo = categoriaRepo;
		this.itemRepo = itemRepo;
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio")
	public CardapioDto cardapioDoRestaurante(@PathVariable("idRestaurante") Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		Cardapio cardapio = cardapioRepo.findByRestaurante(restaurante);
		return new CardapioDto(cardapio);
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}")
	public CardapioDto porId(@PathVariable("idCardapio") Long idCardapio) {
		Cardapio cardapio = cardapioRepo.findById(idCardapio).orElseThrow(() -> new ResourceNotFoundException());
		return new CardapioDto(cardapio);
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}")
	public CategoriaDoCardapioDto categoriaPorId(@PathVariable("idCategoria") Long idCategoria) {
		CategoriaDoCardapio categoria = categoriaRepo.findById(idCategoria)
				.orElseThrow(() -> new ResourceNotFoundException());
		return new CategoriaDoCardapioDto(categoria);
	}

	@PostMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria")
	public CategoriaDoCardapio cardapioDoRestaurante(@PathVariable("idCardapio") Long idCardapio,
			@RequestBody CategoriaDoCardapio categoria) {
		return categoriaRepo.save(categoria);
	}

	@PostMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item")
	public ItemDoCardapioDto adicionaItem(@RequestBody ItemDoCardapio item) {
		return new ItemDoCardapioDto(itemRepo.save(item));
	}

	@PutMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item/{idItem}")
	public ItemDoCardapioDto atualizaItem(@RequestBody ItemDoCardapio item) {
		return new ItemDoCardapioDto(itemRepo.save(item));
	}

	@GetMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item/{idItem}")
	public ItemDoCardapioDto itemPorId(@PathVariable("idItem") Long idItem) {
		ItemDoCardapio item = itemRepo.findById(idItem).orElseThrow(() -> new ResourceNotFoundException());
		return new ItemDoCardapioDto(item);
	}

	@DeleteMapping("/parceiros/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item/{idItem}")
	public void removeItem(@PathVariable("idItem") Long idItem) {
		itemRepo.deleteById(idItem);
	}
}
