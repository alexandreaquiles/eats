package br.com.caelum.eats.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Entrega {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Size(max=100)
	private String nomeDoCliente;
	
	@NotBlank @Pattern(regexp="\\d{8}")
	private String cep;

	private String endereco;

	private String complemento;

	@OneToOne(optional=false)
	private Pedido pedido;

}
