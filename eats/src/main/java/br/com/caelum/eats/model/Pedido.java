package br.com.caelum.eats.model;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
	
	public static enum Status {
		REALIZADO,
		CONFIRMADO,
		PRONTO,
		ENTREGUE;
	}

	private Long id;
	private LocalDateTime dataHora;
	private Status status;
	private Usuario usuario;
	private List<ItemPedido> itens;
	
}
