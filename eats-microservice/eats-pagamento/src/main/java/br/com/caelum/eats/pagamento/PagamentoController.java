package br.com.caelum.eats.pagamento;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.pagamento.pedido.PedidoDto;
import br.com.caelum.eats.pagamento.pedido.PedidoRestClient;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	private PagamentoRepository pagamentoRepo;
	private NotaFiscalService notaFiscal;
	private PedidoRestClient pedidoClient;

	public PagamentoController(PagamentoRepository pagamentoRepo, PedidoRestClient pedidoClient, NotaFiscalService notaFiscal) {
		this.pagamentoRepo = pagamentoRepo;
		this.pedidoClient = pedidoClient;
		this.notaFiscal = notaFiscal;
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
		Long pedidoId = pagamento.getPedidoId();
		pedidoClient.avisaQueFoiPago(pedidoId);
		PedidoDto pedido = pedidoClient.detalhaPorId(pedidoId);
		String nota = notaFiscal.geraNotaPara(pedido);
		System.out.println(nota); //TODO: enviar XML para SEFAZ
		return new PagamentoDto(pagamento);
	}

	@DeleteMapping("/{id}")
	public PagamentoDto cancela(@RequestBody Pagamento pagamento) {
		pagamento.setStatus(Pagamento.Status.CANCELADO);
		pagamentoRepo.save(pagamento);
		return new PagamentoDto(pagamento);
	}

}
