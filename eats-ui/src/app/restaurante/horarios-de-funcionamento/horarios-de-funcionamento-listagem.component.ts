import { Component, OnInit, Input } from '@angular/core';

import { HorarioDeFuncionamentoService } from '../services/horario-de-funcionamento.service';
import { DiaDaSemanaService } from '../services/dia-da-semana.service';

@Component({
  selector: 'app-horarios-de-funcionamento-listagem',
  templateUrl: './horarios-de-funcionamento-listagem.component.html'
})
export class HorariosDeFuncionamentoListagemComponent implements OnInit {

  @Input() restaurante
  horariosDeFuncionamento

  constructor(private horarioDeFuncionamentoService:HorarioDeFuncionamentoService,
              private diaDaSemanaService: DiaDaSemanaService) {
  }

  ngOnInit() {
    if (this.restaurante.id) {
      this.horarioDeFuncionamentoService.todosDoRestaurante(this.restaurante)
        .subscribe(horarios => this.horariosDeFuncionamento = horarios);
    }
  }

  remove(horario) {
    horario.restaurante = this.restaurante;
    this.horarioDeFuncionamentoService.remove(horario).subscribe(() => {
      this.horariosDeFuncionamento = this.horariosDeFuncionamento.filter(h => h !== horario);
    });
  }

}
