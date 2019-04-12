package br.com.caelum.eats.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.repository.RestauranteRepository;

/*
 * Serviço que simula recomendações para um dado CEP.
 * Deve evoluir para uma solução baseada em Data Science.
 * 
 */
@Service
public class RecomendacoesService {

	private RestauranteRepository repo;

	public RecomendacoesService(RestauranteRepository repo) {
		this.repo = repo;
	}
	
	public List<Restaurante> recomendacoesParaOCep(String cep) {
		Pageable limit = PageRequest.of(0,3);
		return repo.findAllByAprovado(true, limit).getContent();
	}

}
