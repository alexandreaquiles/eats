package br.com.caelum.eats.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.model.TipoDeCozinha;
import br.com.caelum.eats.repository.TipoDeCozinhaRepository;

@RestController
@RequestMapping("/admin/tipos-de-cozinha")
public class AdminTipoDeCozinhaController {

	private TipoDeCozinhaRepository repo;

	public AdminTipoDeCozinhaController(TipoDeCozinhaRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	private List<TipoDeCozinha> lista() {
		return repo.findAllByOrderByNomeAsc();
	}

	@PostMapping
	private TipoDeCozinha adiciona(@RequestBody TipoDeCozinha tipoDeCozinha) {
		return repo.save(tipoDeCozinha);
	}

	@PutMapping("/{id}")
	private TipoDeCozinha atualiza(@RequestBody TipoDeCozinha tipoDeCozinha) {
		return repo.save(tipoDeCozinha);
	}

	@DeleteMapping("/{id}")
	private void remove(@PathVariable("id") Long id) {
		repo.deleteById(id);
	}

}
