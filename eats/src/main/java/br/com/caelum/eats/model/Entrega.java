package br.com.caelum.eats.model;

import java.time.LocalDateTime;

public class Entrega {

	private Long id;
	private LocalDateTime dataHora;
	private Pedido pedido;
	private Entregador entregador;

}
