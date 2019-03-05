import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { RestauranteService } from '../services/restaurante.service';
import { FormaDePagamentoService } from '../services/forma-de-pagamento.service';

@Component({
  selector: 'app-formas-de-pagamento-listagem',
  templateUrl: './formas-de-pagamento-listagem.component.html'
})
export class FormasDePagamentoListagemComponent implements OnInit {

  restauranteId
  restaurante
  todasAsFormasDePagamento
  formasDePagamento

  constructor(private route: ActivatedRoute,
    private restauranteService: RestauranteService,
    private formaDePagamentoService:FormaDePagamentoService) {
  }

  ngOnInit() {
    this.restauranteId = this.route.snapshot.params.restauranteId;
    this.restauranteService.porId(this.restauranteId)
      .subscribe(restaurante => this.restaurante = restaurante);

    this.formaDePagamentoService.porIdDoRestaurante(this.restauranteId)
      .subscribe(formasDePagamento => this.formasDePagamento = formasDePagamento);
  }
}
