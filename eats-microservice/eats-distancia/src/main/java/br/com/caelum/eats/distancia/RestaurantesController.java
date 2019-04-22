package br.com.caelum.eats.distancia;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.exception.ResourceNotFoundException;
import br.com.caelum.eats.restaurante.mongo.MongoRestaurante;
import br.com.caelum.eats.restaurante.mongo.MongoRestauranteRepository;

@RestController
public class RestaurantesController {

	private MongoRestauranteRepository repo;

	public RestaurantesController(MongoRestauranteRepository repo) {
		this.repo = repo;
	}

	@PostMapping("/restaurantes")
	public MongoRestaurante adiciona(@RequestBody MongoRestaurante restaurante) {
		return repo.insert(restaurante);
	}

	@PutMapping("/restaurantes/{id}")
	public MongoRestaurante atualiza(@PathVariable Long id, @RequestBody MongoRestaurante restaurante) {
		if (!repo.existsById(id)) {
			throw new ResourceNotFoundException();
		}
		return repo.save(restaurante);
	}

}
