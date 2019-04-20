package br.com.caelum.eats.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {

	Cardapio findByRestaurante(Restaurante restaurante);
}
