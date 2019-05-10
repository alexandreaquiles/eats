package br.com.caelum.notafiscal.services;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import br.com.caelum.notafiscal.pedido.PedidoDto;
import br.com.caelum.notafiscal.pedido.PedidoRestClient;
import lombok.AllArgsConstructor;


import br.com.caelum.notafiscal.AmqpConfig.PagamentoSink;

@Service @AllArgsConstructor
public class ProcessadorDePagamentos {

	private GeradorDeNotaFiscal notaFiscal;
	private PedidoRestClient pedidos;

	@StreamListener(PagamentoSink.PAGAMENTOS_CONFIRMADOS)
	public void processaPagamento(PagamentoConfirmado pagamento) {
		System.out.println(pagamento);
		PedidoDto pedido = pedidos.detalhaPorId(pagamento.getPedidoId());
		String nota = notaFiscal.geraNotaPara(pedido);
		System.out.println(nota); // TODO: enviar XML para SEFAZ
	}
}
