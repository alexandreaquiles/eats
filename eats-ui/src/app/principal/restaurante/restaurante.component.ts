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
  avaliacoes:Array<any> = []
  pedido:any = {
    itens: []
  }
  itemDoPedidoEscolhido

  constructor(private route: ActivatedRoute,
              private restaurantesService: RestaurantesService,
              private cardapioService: CardapioService,
              private avaliacoesService: AvaliacoesService) {
  }

  ngOnInit() {
    this.cep = this.route.snapshot.params.cep;
    this.restauranteId = this.route.snapshot.params.restauranteId;

    this.restaurantesService
      .porId(this.cep, this.restauranteId)
      .subscribe(restaurante => {
        this.restaurante = restaurante;
        this.pedido.restaurante = restaurante;
      });

    this.cardapioService
      .porIdDoRestaurante(this.restauranteId)
      .subscribe(cardapio => this.cardapio = cardapio);

    this.avaliacoesService
      .porIdDoRestaurante(this.restauranteId)
      .subscribe(avaliacoes => this.avaliacoes = avaliacoes);

  }

  escolherItemDoCardapio(itemDoCardapio) {
    this.itemDoPedidoEscolhido = { itemDoCardapio, quantidade: 1 };
  }

  adicionarItemAoPedido() {
    this.pedido.itens.push(this.itemDoPedidoEscolhido);
    this.itemDoPedidoEscolhido = null;
  }

  fazerPedido() {
    // TODO
  }

}
