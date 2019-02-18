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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class FormaDePagamento {

	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	public static enum Tipo {
		DINHEIRO("Dinheiro"),
		CARTAO_CREDITO("Cartão de Crédito"),
		CARTAO_DEBITO("Cartão de Débito"),
		VALE_REFEICAO("Vale Refeição");
		
		private String descricao;
		
		private Tipo(String descricao) {
			this.descricao = descricao;
		}
		
		public String getNome() {
			return name();
		}
		
		public String getDescricao() {
			return descricao;
		}
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull @Enumerated(EnumType.STRING)
	private Tipo tipo;

	@NotBlank @Size(max=100)
	private String nome;

}
