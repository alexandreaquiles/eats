package br.com.caelum.eats.restaurante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caelum.eats.admin.TipoDeCozinha;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	Page<Restaurante> findAllByAprovadoAndTipoDeCozinha(boolean aprovado, TipoDeCozinha tipo, Pageable limit);

	Page<Restaurante> findAllByAprovado(boolean aprovado, Pageable limit);

}
