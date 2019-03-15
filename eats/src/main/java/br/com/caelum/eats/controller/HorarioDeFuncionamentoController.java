package br.com.caelum.eats.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.HorarioDeFuncionamentoDto;
import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.model.HorarioDeFuncionamento;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.HorarioDeFuncionamentoRepository;

@RestController
public class HorarioDeFuncionamentoController {

	private HorarioDeFuncionamentoRepository repo;
	
	public HorarioDeFuncionamentoController(HorarioDeFuncionamentoRepository repo) {
		this.repo = repo;
	}
	
	@GetMapping("/restaurantes/{idRestaurante}/horarios-de-funcionamento/{id}")
	public HorarioDeFuncionamentoDto detalha(@PathVariable("id") Long id) {
		HorarioDeFuncionamento horario = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return new HorarioDeFuncionamentoDto(horario);
	}

	@GetMapping("/restaurantes/{idRestaurante}/horarios-de-funcionamento")
	public List<HorarioDeFuncionamentoDto> lista(@PathVariable("idRestaurante") Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		List<HorarioDeFuncionamento> horariosDoRestaurante = repo.findAllByRestaurante(restaurante);
		return horariosDoRestaurante.stream().map(h -> new HorarioDeFuncionamentoDto(h)).collect(Collectors.toList());
	}

	@PostMapping("/restaurantes/{idRestaurante}/horarios-de-funcionamento")
	private void adiciona(@RequestBody HorarioDeFuncionamento horarioDeFuncionamento) {
		repo.save(horarioDeFuncionamento);
	}

	@PutMapping("/restaurantes/{idRestaurante}/horarios-de-funcionamento/{id}")
	private void atualiza(@RequestBody HorarioDeFuncionamento horarioDeFuncionamento) {
		repo.save(horarioDeFuncionamento);
	}

	@DeleteMapping("/restaurantes/{idRestaurante}/horarios-de-funcionamento/{id}")
	private void remove(@PathVariable("id") Long id) {
		repo.deleteById(id);
	}

}
