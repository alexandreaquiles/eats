package br.com.caelum.eats.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDeCozinhaRepository extends JpaRepository<TipoDeCozinha, Long> {

	List<TipoDeCozinha> findAllByOrderByNomeAsc();

}
