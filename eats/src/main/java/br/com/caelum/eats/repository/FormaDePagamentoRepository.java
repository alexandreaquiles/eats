package br.com.caelum.eats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.caelum.eats.model.FormaDePagamento;
import br.com.caelum.eats.model.Restaurante;

public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Long> {

	List<FormaDePagamento> findAllByOrderByNomeAsc();
	
	@Query("select f from Restaurante r join r.formasDePagamento f where r = :restaurante order by f.nome")
	List<FormaDePagamento> findAllByRestauranteOrderByNomeAsc(Restaurante restaurante);

}
