package br.com.caelum.eats.pagamento.pedido;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PedidoRestClient {

	private String pedidoServiceUrl;
	private RestTemplate restTemplate;

	public PedidoRestClient(RestTemplateBuilder restTemplateBuilder, @Value("${configuracao.pedido.service.url}") String pedidoServiceUrl) {
		this.pedidoServiceUrl = pedidoServiceUrl;
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public PedidoDto detalhaPorId(Long pedidoId) {
		String url = pedidoServiceUrl + "/pedidos/" + pedidoId;
		return restTemplate.getForObject(url, PedidoDto.class);
	}

	public void avisaQueFoiPago(Long pedidoId) {
		String url = pedidoServiceUrl + "/pedidos/" + pedidoId + "/pago";
		restTemplate.put(url, null);
	}

}
