package br.com.caelum.eats.distancia;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
	public ResponseEntity<MongoRestaurante> adiciona(@RequestBody MongoRestaurante restaurante, UriComponentsBuilder uriBuilder) {
		MongoRestaurante salvo = repo.insert(restaurante);
	
		UriComponents uriComponents = uriBuilder.path("/restaurantes/{id}").buildAndExpand(salvo.getId());
		URI uri = uriComponents.toUri();
		return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(salvo);
	}

	@PutMapping("/restaurantes/{id}")
	public MongoRestaurante atualiza(@PathVariable Long id, @RequestBody MongoRestaurante restaurante) {
		if (!repo.existsById(id)) {
			throw new ResourceNotFoundException();
		}
		return repo.save(restaurante);
	}

}
