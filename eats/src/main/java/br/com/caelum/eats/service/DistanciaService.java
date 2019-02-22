package br.com.caelum.eats.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.dto.RestauranteMaisProximo;
import br.com.caelum.eats.repository.RestauranteRepository;

@Service
public class DistanciaService {
	
	private RestauranteRepository repo;

	public DistanciaService(RestauranteRepository repo) {
		this.repo = repo;
	}
	
	public List<RestauranteMaisProximo> restaurantesMaisProximosAoCep(String cep) {
		simulaDemora();
		
		Pageable limit = PageRequest.of(0,5);
		return repo
				.findAll(limit)
				.getContent()
				.stream()
				.map(r -> {
					BigDecimal distancia = new BigDecimal(Math.random()*15);
					return new RestauranteMaisProximo(r.getId(), r.getNome(), r.getTipoDeCozinha(), r.getTaxaDeEntregaEmReais(), r.getTempoDeEntregaMinimoEmMinutos(), r.getTempoDeEntregaMaximoEmMinutos(), distancia);
				})
				.collect(Collectors.toList());
		}

	private void simulaDemora() {
		try {
			//simula demora de até 5s
			Thread.sleep((long) (Math.random()*4000+1000));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
