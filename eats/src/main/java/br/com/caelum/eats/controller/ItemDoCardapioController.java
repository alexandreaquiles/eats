package br.com.caelum.eats.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.ItemDoCardapioDto;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.ItemDoCardapio;
import br.com.caelum.eats.repository.ItemDoCardapioRepository;

@RestController
public class ItemDoCardapioController {

	private ItemDoCardapioRepository repo;

	public ItemDoCardapioController(ItemDoCardapioRepository repo) {
		this.repo = repo;
	}

	@PostMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item")
	public ItemDoCardapioDto adiciona(@RequestBody ItemDoCardapio item) {
		return new ItemDoCardapioDto(repo.save(item));
	}

	@PutMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item/{idItem}")
	public ItemDoCardapioDto atualiza(@RequestBody ItemDoCardapio item) {
		return new ItemDoCardapioDto(repo.save(item));
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item/{idItem}")
	public ItemDoCardapioDto porId(@PathVariable("idItem") Long idItem) {
		ItemDoCardapio item = repo.findById(idItem).orElseThrow(() -> new ResourceNotFoundException());
		return new ItemDoCardapioDto(item);
	}

	@DeleteMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}/item/{idItem}")
	public void remove(@PathVariable("idItem") Long idItem) {
		repo.deleteById(idItem);
	}

}
