import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { RestaurantesService } from '../services/restaurantes.service';
import { CardapioService } from '../services/cardapio.service';
import { AvaliacoesService } from '../services/avaliacoes.service';

@Component({
  selector: 'app-restaurante',
  templateUrl: './restaurante.component.html'
})
export class RestauranteComponent implements OnInit {

  cep: string
  restauranteId: string
  restaurante
  cardapio
  avaliacoes:Array<any>

  constructor(private route: ActivatedRoute,
    private router: Router,
    private restaurantesService: RestaurantesService,
    private cardapioService: CardapioService,
    private avaliacoesService: AvaliacoesService) {
  }

  ngOnInit() {
     this.route.params.subscribe(params => {

      this.cep = params['cep'];
      this.restauranteId = params['restauranteId'];

      this.restaurantesService
        .porId(this.cep, this.restauranteId)
        .subscribe(restaurante => this.restaurante = restaurante);

      this.cardapioService
        .porIdDoRestaurante(this.restauranteId)
        .subscribe(cardapio => this.cardapio = cardapio);

      this.avaliacoesService
        .porIdDoRestaurante(this.restauranteId)
        .subscribe(avaliacoes => this.avaliacoes = avaliacoes);

    });
  }


}
