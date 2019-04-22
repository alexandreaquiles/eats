package br.com.caelum.eats.restaurante.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoRestauranteRepository extends MongoRepository<MongoRestaurante, Long>  {

	Page<MongoRestaurante> findAll(Pageable limit);

	Page<MongoRestaurante> findAllByTipoDeCozinhaId(Long tipoDeCozinhaId, Pageable limit);

}
