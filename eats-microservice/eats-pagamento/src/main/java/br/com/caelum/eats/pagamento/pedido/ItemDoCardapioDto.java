package br.com.caelum.eats.pagamento.pedido;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDoCardapioDto {

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private BigDecimal precoPromocional;

	public BigDecimal getPrecoEfetivo() {
		return precoPromocional != null ? precoPromocional : preco;
	}

}
