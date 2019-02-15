package br.com.caelum.eats.model;

import java.math.BigDecimal;

public class Pagamento {

	public static enum Status {
		CRIADO,
		CONFIRMADO,
		CANCELADO;
	}
	
	private Long id;
	private BigDecimal valor;
	private FormaDePagamento formaDePagamento;
	private Status status;
	private Pedido pedido;

}
