import { Component, OnInit, Input } from '@angular/core';

import { RestaurantesService } from '../services/restaurantes.service';

@Component({
  selector: 'app-detalhes-do-restaurante',
  templateUrl: './detalhes-do-restaurante.component.html'
})
export class DetalhesDoRestauranteComponent implements OnInit {

  @Input() restaurante: any;
  formasDePagamento: Array<any>;
  horariosDeFuncionamento: Array<any>;
  cardapio: any;

  constructor(private restaurantesService: RestaurantesService) {
  }

  ngOnInit() {
    this.restaurantesService.formasDePagamentoDoRestaurante(this.restaurante)
      .subscribe(formas => this.formasDePagamento = formas);

    this.restaurantesService.horariosDeFuncionamentoDoRestaurante(this.restaurante)
      .subscribe(horarios => this.horariosDeFuncionamento = horarios);

    this.restaurantesService.cardapioDoRestaurante(this.restaurante)
      .subscribe(cardapio => this.cardapio = cardapio);
  }

}
