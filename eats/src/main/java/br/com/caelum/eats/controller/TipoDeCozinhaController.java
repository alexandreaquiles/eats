package br.com.caelum.eats.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.model.TipoDeCozinha;
import br.com.caelum.eats.repository.TipoDeCozinhaRepository;

@RestController
@RequestMapping("/tipos-de-cozinha")
public class TipoDeCozinhaController {

	private TipoDeCozinhaRepository repo;

	public TipoDeCozinhaController(TipoDeCozinhaRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	private List<TipoDeCozinha> lista() {
		return repo.findAllByOrderByNomeAsc();
	}

}
