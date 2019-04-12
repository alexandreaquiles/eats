package br.com.caelum.eats.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.caelum.eats.dto.RestauranteComDistancia;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.model.TipoDeCozinha;
import br.com.caelum.eats.repository.RestauranteRepository;

/*
 * Serviço que simula a obtenção dos restaurantes mais próximos a um dado CEP.
 * Deve evoluir para uma solução que utiliza geolocalização.
 * 
 */
@Service
public class DistanciaService {
	
	private RestauranteRepository repo;

	public DistanciaService(RestauranteRepository repo) {
		this.repo = repo;
	}
	
	public List<RestauranteComDistancia> restaurantesMaisProximosAoCep(String cep) {
		Pageable limit = PageRequest.of(0,5);
		return calculaDistanciaParaOsRestaurantes(repo.findAllByAprovado(true, limit).getContent(), cep);
	}

	public List<RestauranteComDistancia> restaurantesDoTipoDeCozinhaMaisProximosAoCep(Long tipoDeCozinhaId, String cep) {
		Pageable limit = PageRequest.of(0,5);
		TipoDeCozinha tipo = new TipoDeCozinha();
		tipo.setId(tipoDeCozinhaId);
		return calculaDistanciaParaOsRestaurantes(repo.findAllByAprovadoAndTipoDeCozinha(true, tipo, limit).getContent(), cep);
	}
	
	public List<RestauranteComDistancia> calculaDistanciaParaOsRestaurantes(List<Restaurante> restaurantes, String cep) {
		return restaurantes
				.stream()
				.map(restaurante -> {
					BigDecimal distancia = distanciaDoCep(restaurante, cep);
					return new RestauranteComDistancia(restaurante, distancia);
				})
				.collect(Collectors.toList());
	}

	public BigDecimal distanciaDoCep(Restaurante restaurante, String cep) {
		return calculaDistancia();
	}

	private BigDecimal calculaDistancia() {
		//simulaDemora();
		return new BigDecimal(Math.random()*15);
	}

	@SuppressWarnings("unused")
	private void simulaDemora() {
		//simula demora de até 5s
		long demora = (long) (Math.random()*4000+1000);
		if (demora % 2 == 0) {
			try {
				Thread.sleep(demora);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
