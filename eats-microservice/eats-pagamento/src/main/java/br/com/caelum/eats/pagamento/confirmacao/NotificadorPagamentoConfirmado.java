package br.com.caelum.eats.pagamento.confirmacao;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.AmqpConfig.PagamentoSource;
import br.com.caelum.eats.pagamento.Pagamento;
import lombok.AllArgsConstructor;

@Service @AllArgsConstructor
public class NotificadorPagamentoConfirmado {

	private PagamentoSource source;

	public void notificaPagamentoConfirmado(Pagamento pagamento) {
		Long pagamentoId = pagamento.getId();
		Long pedidoId = pagamento.getPedidoId();
		PagamentoConfirmado confirmado = new PagamentoConfirmado(pagamentoId, pedidoId);
		System.out.println("enviando para Exchange pagamentosConfirmados");
		source.pagamentosConfirmados().send(MessageBuilder.withPayload(confirmado).build());
	}
}
