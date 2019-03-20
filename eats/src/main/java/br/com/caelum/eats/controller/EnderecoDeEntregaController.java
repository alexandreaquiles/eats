package br.com.caelum.eats.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.EnderecoDeEntregaDto;
import br.com.caelum.eats.model.EnderecoDeEntrega;
import br.com.caelum.eats.repository.EnderecoDeEntregaRepository;

@RestController
@RequestMapping("/entregas")
public class EnderecoDeEntregaController {

	private EnderecoDeEntregaRepository repo;

	public EnderecoDeEntregaController(EnderecoDeEntregaRepository repo) {
		this.repo = repo;
	}

	@PutMapping("/{id}")
	public EnderecoDeEntregaDto atualiza(@RequestBody EnderecoDeEntrega entrega) {
		return new EnderecoDeEntregaDto(repo.save(entrega));
	}
}
