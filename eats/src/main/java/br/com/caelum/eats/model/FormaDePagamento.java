package br.com.caelum.eats.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class FormaDePagamento {

	public static enum Tipo {
		DINHEIRO,
		CARTAO_CREDITO,
		CARTAO_DEBITO,
		VALE_REFEICAO;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull @Enumerated(EnumType.STRING)
	private Tipo tipo;

	@NotBlank @Size(max=100)
	private String nome;

}
