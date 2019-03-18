package br.com.caelum.eats.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.PagamentoDto;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.Pagamento;
import br.com.caelum.eats.repository.PagamentoRepository;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	private PagamentoRepository repo;

	public PagamentoController(PagamentoRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/{id}")
	public PagamentoDto porId(@PathVariable("id") Long id) {
		Pagamento pedido = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new PagamentoDto(pedido);
	}

	@PostMapping
	public PagamentoDto cria(@RequestBody Pagamento pagamento) {
		pagamento.setStatus(Pagamento.Status.CRIADO);
		Pagamento salvo = repo.save(pagamento);
		return new PagamentoDto(salvo);
	}

	@PutMapping("/{id}")
	public PagamentoDto confirma(@RequestBody Pagamento pagamento) {
		pagamento.setStatus(Pagamento.Status.CONFIRMADO);
		repo.save(pagamento);
		return new PagamentoDto(pagamento);
	}

	@DeleteMapping("/{id}")
	public PagamentoDto cancela(@RequestBody Pagamento pagamento) {
		pagamento.setStatus(Pagamento.Status.CANCELADO);
		repo.save(pagamento);
		return new PagamentoDto(pagamento);
	}

}
