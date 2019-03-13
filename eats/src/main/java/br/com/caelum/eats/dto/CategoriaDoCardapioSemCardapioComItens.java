package br.com.caelum.eats.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.eats.model.CategoriaDoCardapio;
import br.com.caelum.eats.model.ItemDoCardapio;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaDoCardapioSemCardapioComItens {

	private Long id;
	private String nome;
	private List<ItemDoCardapioSemCategoria> itens = new ArrayList<>();

	public CategoriaDoCardapioSemCardapioComItens(CategoriaDoCardapio categoria) {
		this(categoria.getId(), categoria.getNome(), tiraCategoriaDosItens(categoria.getItens()));
	}

	private static List<ItemDoCardapioSemCategoria> tiraCategoriaDosItens(List<ItemDoCardapio> itens) {
		return itens.stream().map(i -> new ItemDoCardapioSemCategoria(i)).collect(Collectors.toList());
	}

}
