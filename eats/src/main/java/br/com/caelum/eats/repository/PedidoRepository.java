package br.com.caelum.eats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.eats.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
