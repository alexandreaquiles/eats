package br.com.caelum.eats.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.FormaDePagamento;
import br.com.caelum.eats.repository.FormaDePagamentoRepository;

@RestController
@RequestMapping("/admin/formas-de-pagamento")
public class AdminFormaDePagamentoController {

	private FormaDePagamentoRepository repo;

	public AdminFormaDePagamentoController(FormaDePagamentoRepository repo) {
		this.repo = repo;
	}

	@GetMapping
	private List<FormaDePagamento> lista() {
		return repo.findAllByOrderByNomeAsc();
	}

	@GetMapping("/{id}")
	private FormaDePagamento detalha(@PathVariable("id") Long id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	@GetMapping("/tipos")
	private List<FormaDePagamento.Tipo> tipos() {
		return Arrays.asList(FormaDePagamento.Tipo.values());
	}

	@PostMapping
	private FormaDePagamento adiciona(@RequestBody FormaDePagamento tipoDeCozinha) {
		return repo.save(tipoDeCozinha);
	}

	@PutMapping("/{id}")
	private FormaDePagamento atualiza(@RequestBody FormaDePagamento tipoDeCozinha) {
		return repo.save(tipoDeCozinha);
	}

	@DeleteMapping("/{id}")
	private void remove(@PathVariable("id") Long id) {
		repo.deleteById(id);
	}

}