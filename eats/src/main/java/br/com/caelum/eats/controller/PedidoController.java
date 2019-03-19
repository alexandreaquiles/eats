package br.com.caelum.eats.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.PedidoDto;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.Pedido;
import br.com.caelum.eats.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private PedidoRepository repo;

	public PedidoController(PedidoRepository repo) {
		this.repo = repo;
	}

	@GetMapping("/{id}")
	public PedidoDto porId(@PathVariable("id") Long id) {
		Pedido pedido = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new PedidoDto(pedido);
	}
	
	@PostMapping
	public PedidoDto adiciona(@RequestBody Pedido pedido) {
		pedido.setDataHora(LocalDateTime.now());
		pedido.setStatus(Pedido.Status.REALIZADO);
		pedido.getItens().forEach(item -> item.setPedido(pedido));
		pedido.getEntrega().setPedido(pedido);
		Pedido salvo = repo.save(pedido);
		return new PedidoDto(salvo);
	}

}
