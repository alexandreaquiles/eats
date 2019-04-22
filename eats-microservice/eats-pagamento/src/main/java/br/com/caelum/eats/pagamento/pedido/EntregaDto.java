package br.com.caelum.eats.pagamento.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntregaDto {

	private Long id;
	private Cliente cliente;
	private String cep;
	private String endereco;
	private String complemento;

}
