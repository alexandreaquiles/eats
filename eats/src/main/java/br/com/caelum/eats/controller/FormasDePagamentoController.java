package br.com.caelum.eats.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.model.FormaDePagamento;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.FormaDePagamentoRepository;

@RestController
public class FormasDePagamentoController {

	private FormaDePagamentoRepository repo;

	public FormasDePagamentoController(FormaDePagamentoRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/formas-de-pagamento")
	private List<FormaDePagamento> lista() {
		return repo.findAllByOrderByNomeAsc();
	}

	@GetMapping("/restaurantes/{idRestaurante}/formas-de-pagamento")
	public List<FormaDePagamento> lista(@PathVariable("idRestaurante") Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		List<FormaDePagamento> formasDePagamentoDoRestaurante = repo.findAllByRestauranteOrderByNomeAsc(restaurante);
		return formasDePagamentoDoRestaurante;
	}

}
