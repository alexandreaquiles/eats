package br.com.caelum.eats.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.CategoriaDoCardapioSemCardapioComItens;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.CategoriaDoCardapio;
import br.com.caelum.eats.repository.CategoriaDoCardapioRepository;

@RestController
public class CategoriaDoCardapioController {

	private CategoriaDoCardapioRepository repo;

	public CategoriaDoCardapioController(CategoriaDoCardapioRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria/{idCategoria}")
	public CategoriaDoCardapioSemCardapioComItens porId(@PathVariable("idCategoria") Long idCategoria) {
		CategoriaDoCardapio categoria = repo.findById(idCategoria).orElseThrow(() -> new ResourceNotFoundException());
		return new CategoriaDoCardapioSemCardapioComItens(categoria);
	}

	@PostMapping("/restaurantes/{idRestaurante}/cardapio/{idCardapio}/categoria")
	public CategoriaDoCardapio cardapioDoRestaurante(@PathVariable("idCardapio") Long idCardapio, @RequestBody CategoriaDoCardapio categoria) {
		return repo.save(categoria);
	}

}
