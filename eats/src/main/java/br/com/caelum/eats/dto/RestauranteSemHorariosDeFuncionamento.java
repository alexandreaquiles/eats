package br.com.caelum.eats.dto;

import java.math.BigDecimal;

import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.model.TipoDeCozinha;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestauranteSemHorariosDeFuncionamento {

	private Long id;

	private String cnpj;
	
	private String nome;
	
	private String descricao;
	
	private BigDecimal taxaDeEntregaEmReais;
	
	private Integer tempoDeEntregaMinimoEmMinutos;
	
	private Integer tempoDeEntregaMaximoEmMinutos;
	
	private Boolean aprovado;

	private TipoDeCozinha tipoDeCozinha;
	
	public RestauranteSemHorariosDeFuncionamento(Restaurante restaurante) {
		this(restaurante.getId(), restaurante.getCnpj(), restaurante.getNome(), restaurante.getDescricao(), restaurante.getTaxaDeEntregaEmReais(), restaurante.getTempoDeEntregaMinimoEmMinutos(), restaurante.getTempoDeEntregaMinimoEmMinutos(), restaurante.getAprovado(), restaurante.getTipoDeCozinha());
	}

}
