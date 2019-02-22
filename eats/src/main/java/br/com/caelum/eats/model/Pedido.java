package br.com.caelum.eats.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido {
	
	public static enum Status {
		REALIZADO,
		CONFIRMADO,
		PRONTO,
		ENTREGUE;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime dataHora;

	@NotNull @Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy="pedido")
	private List<ItemPedido> itens;

//	private Usuario usuario;

}
