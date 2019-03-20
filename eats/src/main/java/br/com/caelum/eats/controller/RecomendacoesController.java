package br.com.caelum.eats.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.service.RecomendacoesService;

@RestController
@RequestMapping("/recomendacoes")
public class RecomendacoesController {

	private RecomendacoesService recomendacoes;

	public RecomendacoesController(RecomendacoesService recomendacoes) {
		this.recomendacoes = recomendacoes;
	}

	@GetMapping("/{cep}")
	public List<Restaurante> maisProximos(@PathVariable("cep") String cep) {
		return recomendacoes.recomendacoesParaOCep(cep);
	}

}
