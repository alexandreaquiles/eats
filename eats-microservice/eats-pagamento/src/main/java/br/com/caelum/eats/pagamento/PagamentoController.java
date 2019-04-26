package br.com.caelum.eats.pagamento;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
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
	public Resource<PagamentoDto> detalha(@PathVariable("id") Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException());

		List<Link> links = new ArrayList<>();
		
		Link self = linkTo(methodOn(PagamentoController.class).detalha(id)).withSelfRel();
		links.add(self);

		if (Pagamento.Status.CRIADO.equals(pagamento.getStatus())) {
			Link confirma = linkTo(methodOn(PagamentoController.class).confirma(pagamento, id)).withRel("confirma");
			links.add(confirma);

			Link cancela = linkTo(methodOn(PagamentoController.class).cancela(pagamento, id)).withRel("cancela");
			links.add(cancela);
		}

		PagamentoDto dto = new PagamentoDto(pagamento);
		Resource<PagamentoDto> resource = new Resource<PagamentoDto>(dto, links);

		return resource;
	}

	@PostMapping
	public ResponseEntity<Resource<PagamentoDto>> cria(@RequestBody Pagamento pagamento, UriComponentsBuilder uriBuilder) {
		pagamento.setStatus(Pagamento.Status.CRIADO);
		Pagamento salvo = pagamentoRepo.save(pagamento);
		Long id = salvo.getId();
		PagamentoDto dto = new PagamentoDto(salvo);

		UriComponents uriComponents = uriBuilder.path("/pagamentos/{id}").buildAndExpand(salvo.getId());
		URI uri = uriComponents.toUri();

		List<Link> links = new ArrayList<>();
		
		Link self = linkTo(methodOn(PagamentoController.class).detalha(id)).withSelfRel();
		links.add(self);

		Link confirma = linkTo(methodOn(PagamentoController.class).confirma(pagamento, id)).withRel("confirma");
		links.add(confirma);

		Link cancela = linkTo(methodOn(PagamentoController.class).cancela(pagamento, id)).withRel("cancela");
		links.add(cancela);

		Resource<PagamentoDto> resource = new Resource<PagamentoDto>(dto, links);
		return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(resource);
	}

	@PutMapping("/{id}")
	public Resource<PagamentoDto> confirma(@RequestBody Pagamento pagamento, @PathVariable Long id) {
		pagamento.setStatus(Pagamento.Status.CONFIRMADO);
		pagamentoRepo.save(pagamento);
		Long pedidoId = pagamento.getPedidoId();
		pedidoClient.avisaQueFoiPago(pedidoId);
		PedidoDto pedido = pedidoClient.detalhaPorId(pedidoId);
		String nota = notaFiscal.geraNotaPara(pedido);
		System.out.println(nota); // TODO: enviar XML para SEFAZ

		List<Link> links = new ArrayList<>();
		
		Link self = linkTo(methodOn(PagamentoController.class).detalha(id)).withSelfRel();
		links.add(self);

		PagamentoDto dto = new PagamentoDto(pagamento);
		Resource<PagamentoDto> resource = new Resource<PagamentoDto>(dto, links);

		return resource;
	}

	@DeleteMapping("/{id}")
	public Resource<PagamentoDto> cancela(@RequestBody Pagamento pagamento, @PathVariable Long id) {
		pagamento.setStatus(Pagamento.Status.CANCELADO);
		pagamentoRepo.save(pagamento);
		List<Link> links = new ArrayList<>();
		
		Link self = linkTo(methodOn(PagamentoController.class).detalha(id)).withSelfRel();
		links.add(self);

		PagamentoDto dto = new PagamentoDto(pagamento);
		Resource<PagamentoDto> resource = new Resource<PagamentoDto>(dto, links);

		return resource;
	}

}
