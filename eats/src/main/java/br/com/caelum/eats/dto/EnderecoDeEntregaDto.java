package br.com.caelum.eats.dto;

import br.com.caelum.eats.model.EnderecoDeEntrega;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoDeEntregaDto {

	private Long id;
	private String cep;
	private String endereco;
	private String complemento;

	public EnderecoDeEntregaDto(EnderecoDeEntrega entrega) {
		this(entrega.getId(), entrega.getCep(), entrega.getEndereco(), entrega.getComplemento());
	}

}
