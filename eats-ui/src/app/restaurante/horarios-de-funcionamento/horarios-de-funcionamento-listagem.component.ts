import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { HorarioDeFuncionamentoService } from '../services/horario-de-funcionamento.service';
import { RestauranteService } from '../services/restaurante.service';
import { DiaDaSemanaService } from '../services/dia-da-semana.service';

@Component({
  selector: 'app-horarios-de-funcionamento-listagem',
  templateUrl: './horarios-de-funcionamento-listagem.component.html'
})
export class HorariosDeFuncionamentoListagemComponent implements OnInit {

  restaurante
  horariosDeFuncionamento

  constructor(private route: ActivatedRoute,
              private restauranteService: RestauranteService,
              private horarioDeFuncionamentoService:HorarioDeFuncionamentoService,
              private diaDaSemanaService: DiaDaSemanaService) {
  }

  ngOnInit() {
    const restauranteId = this.route.snapshot.params.restauranteId;
    this.restaurante = {id: restauranteId};

    this.restauranteService.porId(restauranteId)
      .subscribe(restaurante => this.restaurante = restaurante);

    this.horarioDeFuncionamentoService.todosDoRestaurante({id: restauranteId})
      .subscribe(horarios => this.horariosDeFuncionamento = horarios);

  }

  remove(horario) {
    this.horarioDeFuncionamentoService.remove(this.restaurante, horario).subscribe(() => {
      this.horariosDeFuncionamento = this.horariosDeFuncionamento.filter(h => h != horario);
    });
  }

}
