package br.com.caelum.eats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.caelum.eats.model.FormaDePagamento;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.model.RestauranteFormaDePagamento;
import br.com.caelum.eats.model.RestauranteFormaDePagamento.RestauranteFormaDePagamentoId;

public interface RestauranteFormaDePagamentoRepository extends JpaRepository<RestauranteFormaDePagamento, RestauranteFormaDePagamentoId> {

	@Query("select f from RestauranteFormaDePagamento rf join rf.restaurante r join rf.formaDePagamento f where r = :restaurante order by f.nome")
	List<FormaDePagamento> findAllByRestauranteOrderByNomeAsc(Restaurante restaurante);

}
