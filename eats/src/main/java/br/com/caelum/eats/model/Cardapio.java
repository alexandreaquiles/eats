package br.com.caelum.eats.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Cardapio {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne(optional=false)
	private Restaurante restaurante;

}
