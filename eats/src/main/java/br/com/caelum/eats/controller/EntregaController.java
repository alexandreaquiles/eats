package br.com.caelum.eats.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.EntregaDto;
import br.com.caelum.eats.model.Entrega;
import br.com.caelum.eats.repository.EntregaRepository;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository repo;

	public EntregaController(EntregaRepository repo) {
		this.repo = repo;
	}

	@PutMapping("/{id}")
	public EntregaDto atualiza(@RequestBody Entrega entrega) {
		return new EntregaDto(repo.save(entrega));
	}
}
