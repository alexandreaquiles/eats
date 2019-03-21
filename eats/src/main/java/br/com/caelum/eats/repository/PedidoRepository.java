package br.com.caelum.eats.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.caelum.eats.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select p from Pedido p where p.status not in :listaDeStatus")
	List<Pedido> semStatus(List<Pedido.Status> listaDeStatus);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Pedido p set p.status = :status where p = :pedido")
	void atualizaStatus(Pedido.Status status, Pedido pedido);


}
