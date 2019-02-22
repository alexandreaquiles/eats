package br.com.caelum.eats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.eats.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
