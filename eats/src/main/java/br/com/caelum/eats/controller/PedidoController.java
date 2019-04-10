package br.com.caelum.eats.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.PedidoDto;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.Pedido;
import br.com.caelum.eats.model.Pedido.Status;
import br.com.caelum.eats.repository.PedidoRepository;

@RestController
public class PedidoController {

	private PedidoRepository repo;
	private SimpMessagingTemplate websocket;

	public PedidoController(PedidoRepository repo, SimpMessagingTemplate websocket) {
		this.repo = repo;
		this.websocket = websocket;
	}
	
	@GetMapping("/pedidos")
	public List<PedidoDto> lista() {
		return repo.findAll().stream()
				.map(pedido -> new PedidoDto(pedido)).collect(Collectors.toList());
	}


	@GetMapping("/pedidos/{id}")
	public PedidoDto porId(@PathVariable("id") Long id) {
		Pedido pedido = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new PedidoDto(pedido);
	}

	@PostMapping("/pedidos")
	public PedidoDto adiciona(@RequestBody Pedido pedido) {
		pedido.setDataHora(LocalDateTime.now());
		pedido.setStatus(Pedido.Status.REALIZADO);
		pedido.getItens().forEach(item -> item.setPedido(pedido));
		pedido.getEntrega().setPedido(pedido);
		Pedido salvo = repo.save(pedido);
		return new PedidoDto(salvo);
	}

	@GetMapping("/parceiros/pedidos/pendentes")
	public List<PedidoDto> pendentes() {
		return repo.semStatus(Arrays.asList(Status.REALIZADO, Status.ENTREGUE)).stream()
				.map(pedido -> new PedidoDto(pedido)).collect(Collectors.toList());
	}

	@PutMapping("/parceiros/pedidos/{id}/status")
	public PedidoDto atualizaStatus(@RequestBody Pedido pedido) {
		repo.atualizaStatus(pedido.getStatus(), pedido);
		this.websocket.convertAndSend("/pedidos/status", pedido);
		return new PedidoDto(pedido);
	}

}
