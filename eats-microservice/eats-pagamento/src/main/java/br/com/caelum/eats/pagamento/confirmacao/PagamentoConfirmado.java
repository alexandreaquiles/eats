package br.com.caelum.eats.pagamento.confirmacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PagamentoConfirmado {

	private Long pagamentoId;
	private Long pedidoId;

}
