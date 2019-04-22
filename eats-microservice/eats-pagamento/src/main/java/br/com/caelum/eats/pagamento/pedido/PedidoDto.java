package br.com.caelum.eats.pagamento.pedido;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoDto {

	private Long id;
	private EntregaDto entrega;
	private List<ItemDoPedidoDto> itens = new ArrayList<>();
	
}
