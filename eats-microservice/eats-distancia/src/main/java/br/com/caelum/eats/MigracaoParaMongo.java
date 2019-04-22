package br.com.caelum.eats;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.caelum.eats.restaurante.Restaurante;
import br.com.caelum.eats.restaurante.RestauranteRepository;
import br.com.caelum.eats.restaurante.mongo.MongoRestaurante;
import br.com.caelum.eats.restaurante.mongo.MongoRestauranteRepository;

@Configuration
public class MigracaoParaMongo implements CommandLineRunner {

	private RestauranteRepository jpaRestauranteRepo;
	private MongoRestauranteRepository mongoRestauranteRepo;
	
	public MigracaoParaMongo(RestauranteRepository jpaRestauranteRepo, MongoRestauranteRepository mongoRestauranteRepo) {
		this.jpaRestauranteRepo = jpaRestauranteRepo;
		this.mongoRestauranteRepo = mongoRestauranteRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Iniciando a migração de restaurantes aprovados para Mongo");
		for (Restaurante restauranteJpa : jpaRestauranteRepo.findAllByAprovado(true)) {
			Long id = restauranteJpa.getId();
			String cep = restauranteJpa.getCep();
			Long tipoDeCozinhaId = restauranteJpa.getTipoDeCozinhaId();
			if (!mongoRestauranteRepo.existsById(id)) {
				MongoRestaurante restauranteMongo = new MongoRestaurante(id, cep, tipoDeCozinhaId);
				mongoRestauranteRepo.insert(restauranteMongo);
				System.out.println("Migrado para Mongo: " + restauranteMongo);
			}
		}
		System.out.println("Fim da migração de restaurantes aprovados para Mongo");
	}

}
