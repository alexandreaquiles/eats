package br.com.caelum.apigateway;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.pedido.PedidoDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatusDoPedidoService {

	private SimpMessagingTemplate websocket;

	@StreamListener(AmqpConfig.AtualizacaoPedidoSink.PEDIDO_COM_STATUS_ATUALIZADO)
	public void pedidoAtualizado(PedidoDto pedido) {
		System.out.println("Pedido atualizado: " + pedido);
		
		websocket.convertAndSend("/pedidos/"+pedido.getId()+"/status", pedido);
		if ("PAGO".equals(pedido.getStatus())) {
			System.out.println("PEDIDO PAGO.");
			websocket.convertAndSend("/parceiros/restaurantes/"+pedido.getRestaurante().getId()+"/pedidos/pendentes", pedido);
		}
	}

}
