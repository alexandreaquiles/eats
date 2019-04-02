import { Component, OnInit, Input } from '@angular/core';

import { FormaDePagamentoService } from 'src/app/services/forma-de-pagamento.service';
import { HorarioDeFuncionamentoService } from 'src/app/services/horario-de-funcionamento.service';
import { CardapioService } from 'src/app/services/cardapio.service';
import { DiaDaSemanaService } from 'src/app/services/dia-da-semana.service';

@Component({
  selector: 'app-detalhes-do-restaurante',
  templateUrl: './detalhes-do-restaurante.component.html'
})
export class DetalhesDoRestauranteComponent implements OnInit {

  @Input() restaurante: any;
  formasDePagamento: Array<any>;
  horariosDeFuncionamento: Array<any>;
  cardapio: any;

  constructor(private formaDePagamentoService: FormaDePagamentoService,
              private horarioDeFuncionamentoService: HorarioDeFuncionamentoService,
              private cardapioService: CardapioService,
              private diaDaSemanaService: DiaDaSemanaService) {
  }

  ngOnInit() {
    this.formaDePagamentoService.doRestaurante(this.restaurante)
      .subscribe(formas => this.formasDePagamento = formas);

    this.horarioDeFuncionamentoService.todosDoRestaurante(this.restaurante)
      .subscribe(horarios => this.horariosDeFuncionamento = horarios);

    this.cardapioService.doRestaurante(this.restaurante)
      .subscribe(cardapio => this.cardapio = cardapio);
  }

}
