import { Component, OnInit } from '@angular/core';

import { RestaurantesService } from '../services/restaurantes.service';

@Component({
  selector: 'app-restaurantes-em-aprovacao',
  templateUrl: './restaurantes-em-aprovacao.component.html'
})
export class RestauranteEmAprovacaoComponent implements OnInit {

  restaurantesEmAprovacao:Array<any> = []
  restauranteEmDetalhe
  formasDePagamento
  horariosDeFuncionamento
  cardapio

  constructor(private restaurantesService: RestaurantesService) {
  }

  ngOnInit() {
    this.restaurantesService.emAprovacao()
      .subscribe(restaurantes => this.restaurantesEmAprovacao = restaurantes);
  }

  detalha(restaurante) {
    this.restauranteEmDetalhe = restaurante;

    this.restaurantesService.formasDePagamentoDoRestaurante(restaurante)
      .subscribe(formas => this.formasDePagamento = formas);

    this.restaurantesService.horariosDeFuncionamentoDoRestaurante(restaurante)
      .subscribe(horarios => this.horariosDeFuncionamento = horarios);

    this.restaurantesService.cardapioDoRestaurante(restaurante)
      .subscribe(cardapio => this.cardapio = cardapio);
  }

  aprova(restaurante) {
    this.restaurantesService.aprova(restaurante)
      .subscribe(() => {
        restaurante.aprovado = true;
        this.restauranteEmDetalhe = null;
      });
  }
}
