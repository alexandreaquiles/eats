package br.com.caelum.eats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.caelum.eats.model.FormaDePagamento;

public interface AvaliacoesRepository  extends JpaRepository<FormaDePagamento, Long> {

	@Query("select avg(a.nota) from Avaliacao a where a.restaurante.id =:restauranteId")
	public Double mediaDoRestaurantePeloId(@Param("restauranteId") Long restauranteId);

}
