package br.com.caelum.eats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.caelum.eats.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select p from Pedido p where p.status != :status")
	List<Pedido> semStatus(Pedido.Status status);

}
