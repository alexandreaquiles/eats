package br.com.caelum.eats.pagamento;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.eats.exception.ResourceNotFoundException;
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
	
	@GetMapping("/{id}")
	public PagamentoDto detalha(@PathVariable("id") Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new PagamentoDto(pagamento);
	}

	@PostMapping
	public ResponseEntity<PagamentoDto> cria(@RequestBody Pagamento pagamento, UriComponentsBuilder uriBuilder) {
		pagamento.setStatus(Pagamento.Status.CRIADO);
		Pagamento salvo = pagamentoRepo.save(pagamento);
		UriComponents uriComponents = uriBuilder.path("/pagamentos/{id}").buildAndExpand(salvo.getId());
		URI uri = uriComponents.toUri();
		return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(new PagamentoDto(salvo));
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
