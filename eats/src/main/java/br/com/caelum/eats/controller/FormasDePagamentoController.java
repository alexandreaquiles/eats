package br.com.caelum.eats.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.model.FormaDePagamento;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.model.RestauranteFormaDePagamento;
import br.com.caelum.eats.model.RestauranteFormaDePagamento.RestauranteFormaDePagamentoId;
import br.com.caelum.eats.repository.FormaDePagamentoRepository;
import br.com.caelum.eats.repository.RestauranteFormaDePagamentoRepository;

@RestController
public class FormasDePagamentoController {

	private FormaDePagamentoRepository formaRepo;
	private RestauranteFormaDePagamentoRepository restauranteFormaDePagamentoRepo;
	
	public FormasDePagamentoController(FormaDePagamentoRepository formaRepo, RestauranteFormaDePagamentoRepository restauranteFormaDePagamentoRepo) {
		this.formaRepo = formaRepo;
		this.restauranteFormaDePagamentoRepo = restauranteFormaDePagamentoRepo;
	}

	@GetMapping("/formas-de-pagamento")
	private List<FormaDePagamento> lista() {
		return formaRepo.findAllByOrderByNomeAsc();
	}

	@GetMapping("/restaurantes/{idRestaurante}/formas-de-pagamento")
	public List<FormaDePagamento> lista(@PathVariable("idRestaurante") Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		List<FormaDePagamento> formasDePagamentoDoRestaurante = restauranteFormaDePagamentoRepo.findAllByRestauranteOrderByNomeAsc(restaurante);
		return formasDePagamentoDoRestaurante;
	}
	
	@PostMapping("/restaurantes/{idRestaurante}/formas-de-pagamento")
	public void adiciona(@PathVariable("idRestaurante") Long idRestaurante, @RequestBody FormaDePagamento formaDePagamento) {
		RestauranteFormaDePagamentoId id = new RestauranteFormaDePagamentoId(idRestaurante, formaDePagamento.getId());
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		RestauranteFormaDePagamento restauranteFormaDePagamento = new RestauranteFormaDePagamento(id, restaurante, formaDePagamento);
		restauranteFormaDePagamentoRepo.save(restauranteFormaDePagamento);
	}

	@DeleteMapping("/restaurantes/{idRestaurante}/formas-de-pagamento/{idFormaDePagamento}")
	public void remove(@PathVariable("idRestaurante") Long idRestaurante, @PathVariable("idFormaDePagamento") Long idFormaDePagamento) {
		RestauranteFormaDePagamentoId id = new RestauranteFormaDePagamentoId(idRestaurante, idFormaDePagamento);
		restauranteFormaDePagamentoRepo.deleteById(id);
	}

}
