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
import br.com.caelum.eats.model.Pedido;
import br.com.caelum.eats.model.Pedido.Status;
import br.com.caelum.eats.repository.PagamentoRepository;
import br.com.caelum.eats.repository.PedidoRepository;
import br.com.caelum.eats.service.NotaFiscalService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	private PagamentoRepository pagamentoRepo;
	private PedidoRepository pedidoRepo;
	private NotaFiscalService notaFiscal;

	public PagamentoController(PagamentoRepository pagamentoRepo, PedidoRepository pedidoRepo, NotaFiscalService notaFiscal) {
		this.pagamentoRepo = pagamentoRepo;
		this.pedidoRepo = pedidoRepo;
		this.notaFiscal = notaFiscal;
	}

	@GetMapping("/{id}")
	public PagamentoDto porId(@PathVariable("id") Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new PagamentoDto(pagamento);
	}

	@PostMapping
	public PagamentoDto cria(@RequestBody Pagamento pagamento) {
		pagamento.setStatus(Pagamento.Status.CRIADO);
		Pagamento salvo = pagamentoRepo.save(pagamento);
		return new PagamentoDto(salvo);
	}

	@PutMapping("/{id}")
	public PagamentoDto confirma(@RequestBody Pagamento pagamento) {
		pagamento.setStatus(Pagamento.Status.CONFIRMADO);
		pagamentoRepo.save(pagamento);
		Pedido pedido = pagamento.getPedido();
		pedidoRepo.atualizaStatus(Status.PAGO, pedido);
		String nota = notaFiscal.geraNotaPara(pedido);
		System.out.println(nota);
		//TODO: enviar XML para SEFAZ
		return new PagamentoDto(pagamento);
	}

	@DeleteMapping("/{id}")
	public PagamentoDto cancela(@RequestBody Pagamento pagamento) {
		pagamento.setStatus(Pagamento.Status.CANCELADO);
		pagamentoRepo.save(pagamento);
		return new PagamentoDto(pagamento);
	}

}
