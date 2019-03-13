package br.com.caelum.eats.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.eats.model.Cardapio;
import br.com.caelum.eats.model.CategoriaDoCardapio;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CardapioSemRestaurante {

	private Long id;
	private List<CategoriaDoCardapioSemCardapioComItens> categorias = new ArrayList<>();

	public CardapioSemRestaurante(Cardapio cardapio) {
		this(cardapio.getId(), tiraCardapioDasCategorias(cardapio.getCategorias()));
	}

	private static List<CategoriaDoCardapioSemCardapioComItens> tiraCardapioDasCategorias(List<CategoriaDoCardapio> categorias) {
		return categorias.stream().map(c -> new CategoriaDoCardapioSemCardapioComItens(c)).collect(Collectors.toList());
	}
}
