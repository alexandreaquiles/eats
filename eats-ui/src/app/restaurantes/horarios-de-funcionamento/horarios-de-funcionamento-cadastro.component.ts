import { Component } from "@angular/core";
import { ActivatedRoute, Router } from '@angular/router';

import { HorarioDeFuncionamentoService } from '../services/horario-de-funcionamento.service';
import { RestauranteService } from '../services/restaurante.service';
import { DiaDaSemanaService } from '../services/dia-da-semana.service';

@Component({
  selector: 'app-horarios-de-funcionamento-cadastro',
  templateUrl: './horarios-de-funcionamento-cadastro.component.html'
})
export class HorariosDeFuncionamentoCadastroComponent {

  restaurante:any = {}
  horarioDeFuncionamento:any = {}

  constructor(private route: ActivatedRoute,
              private router: Router,
              private restauranteService: RestauranteService,
              private horarioDeFuncionamentoService:HorarioDeFuncionamentoService,
              private diaDaSemanaService: DiaDaSemanaService) {
  }

  ngOnInit() {
    const restauranteId = this.route.snapshot.params.restauranteId;

    this.restauranteService.porId(restauranteId)
      .subscribe(restaurante => this.restaurante = restaurante);

    const horarioId = this.route.snapshot.params.horarioId;

    if (horarioId) {
      this.horarioDeFuncionamentoService.porId(restauranteId, horarioId)
        .subscribe(horario => this.horarioDeFuncionamento = horario);
    }

  }

  salva() {
    this.horarioDeFuncionamento.restaurante = this.restaurante;
    this.horarioDeFuncionamentoService.salva( this.horarioDeFuncionamento)
      .subscribe(() => this.router.navigate([`/restaurantes/${this.restaurante.id}`]));
  }

}
