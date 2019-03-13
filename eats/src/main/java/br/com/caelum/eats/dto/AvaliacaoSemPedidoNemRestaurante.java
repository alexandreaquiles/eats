package br.com.caelum.eats.dto;

import br.com.caelum.eats.model.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvaliacaoSemPedidoNemRestaurante {

	private Long id;
	private Integer nota;
	private String comentario;

	public AvaliacaoSemPedidoNemRestaurante(Avaliacao avaliacao) {
		this(avaliacao.getId(), avaliacao.getNota(), avaliacao.getComentario());
	}
}
