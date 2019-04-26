package br.com.caelum.eats.pagamento;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
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

	public PagamentoController(PagamentoRepository pagamentoRepo, PedidoRestClient pedidoClient,
			NotaFiscalService notaFiscal) {
		this.pagamentoRepo = pagamentoRepo;
		this.pedidoClient = pedidoClient;
		this.notaFiscal = notaFiscal;
	}

	@GetMapping("/{id}")
	public EntityModel<PagamentoDto> detalha(@PathVariable("id") Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		PagamentoDto dto = new PagamentoDto(pagamento);

		Link selfRel = linkTo(methodOn(PagamentoController.class).detalha(id)).withSelfRel()
				.andAffordance(afford(methodOn(PagamentoController.class).confirma(null, id)))
				.andAffordance(afford(methodOn(PagamentoController.class).cancela(null, id)));

		Link confirmaRel = linkTo(methodOn(PagamentoController.class).confirma(null, id)).withRel("confirma")
				.andAffordance(afford(methodOn(PagamentoController.class).confirma(null, id)))
				.andAffordance(afford(methodOn(PagamentoController.class).cancela(null, id)));

		Link cancelaRel = linkTo(methodOn(PagamentoController.class).cancela(null, id)).withRel("cancela")
				.andAffordance(afford(methodOn(PagamentoController.class).confirma(null, id)))
				.andAffordance(afford(methodOn(PagamentoController.class).cancela(null, id)));

		EntityModel<PagamentoDto> resource = new EntityModel<PagamentoDto>(dto, selfRel, confirmaRel, cancelaRel);

		return resource;
	}

	@PostMapping
	public ResponseEntity<EntityModel<PagamentoDto>> cria(@RequestBody Pagamento pagamento, UriComponentsBuilder uriBuilder) {
		pagamento.setStatus(Pagamento.Status.CRIADO);
		Pagamento salvo = pagamentoRepo.save(pagamento);
		Long id = salvo.getId();
		PagamentoDto dto = new PagamentoDto(salvo);
		

		UriComponents uriComponents = uriBuilder.path("/pagamentos/{id}").buildAndExpand(salvo.getId());
		URI uri = uriComponents.toUri();

		Link selfRel = linkTo(methodOn(PagamentoController.class).detalha(id)).withSelfRel()
				.andAffordance(afford(methodOn(PagamentoController.class).confirma(salvo, id)))
				.andAffordance(afford(methodOn(PagamentoController.class).cancela(salvo, id)));

		Link confirmaRel = linkTo(methodOn(PagamentoController.class).confirma(null, id)).withRel("confirma")
				.andAffordance(afford(methodOn(PagamentoController.class).confirma(salvo, id)))
				.andAffordance(afford(methodOn(PagamentoController.class).cancela(salvo, id)));

		Link cancelaRel = linkTo(methodOn(PagamentoController.class).cancela(null, id)).withRel("cancela")
				.andAffordance(afford(methodOn(PagamentoController.class).confirma(salvo, id)))
				.andAffordance(afford(methodOn(PagamentoController.class).cancela(salvo, id)));


		EntityModel<PagamentoDto> resource = new EntityModel<PagamentoDto>(dto, selfRel, confirmaRel, cancelaRel);
		return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
	}

	@PutMapping("/{id}")
	public PagamentoDto confirma(@RequestBody Pagamento pagamento, @PathVariable Long id) {
		pagamento.setStatus(Pagamento.Status.CONFIRMADO);
		pagamentoRepo.save(pagamento);
		Long pedidoId = pagamento.getPedidoId();
		pedidoClient.avisaQueFoiPago(pedidoId);
		PedidoDto pedido = pedidoClient.detalhaPorId(pedidoId);
		String nota = notaFiscal.geraNotaPara(pedido);
		System.out.println(nota); // TODO: enviar XML para SEFAZ
		return new PagamentoDto(pagamento);
	}

	@DeleteMapping("/{id}")
	public PagamentoDto cancela(@RequestBody Pagamento pagamento, @PathVariable Long id) {
		pagamento.setStatus(Pagamento.Status.CANCELADO);
		pagamentoRepo.save(pagamento);
		return new PagamentoDto(pagamento);
	}

}
