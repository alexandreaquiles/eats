package br.com.caelum.eats.pagamento.pedido;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="monolito")
public interface PedidoRestClient {

	@GetMapping("/pedidos/{pedidoId}")
	public PedidoDto detalhaPorId(@PathVariable("pedidoId") Long pedidoId);

	@PutMapping("/pedidos/{pedidoId}/pago")
	public void avisaQueFoiPago(@PathVariable("pedidoId") Long pedidoId);

}
