package br.com.caelum.eats.restaurante.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRestauranteRepository extends MongoRepository<MongoRestaurante, Long>  {

}
