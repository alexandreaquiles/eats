package br.com.caelum.eats.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.eats.model.FormaDePagamento;

public interface FormaDePagamentoRepository extends JpaRepository<FormaDePagamento, Long> {

}
