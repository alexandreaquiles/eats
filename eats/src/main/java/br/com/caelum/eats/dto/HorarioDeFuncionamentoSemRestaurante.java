package br.com.caelum.eats.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import br.com.caelum.eats.model.HorarioDeFuncionamento;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HorarioDeFuncionamentoSemRestaurante {

	private Long id;

	private DayOfWeek diaDaSemana;

	private LocalTime horarioDeAbertura;

	private LocalTime horarioDeFechamento;

	public HorarioDeFuncionamentoSemRestaurante(HorarioDeFuncionamento horario) {
		this(horario.getId(), horario.getDiaDaSemana(), horario.getHorarioDeAbertura(), horario.getHorarioDeFechamento());
	}
}
