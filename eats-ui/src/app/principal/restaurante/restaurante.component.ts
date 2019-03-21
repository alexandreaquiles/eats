import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { RestaurantesService } from '../services/restaurantes.service';
import { CardapioService } from '../services/cardapio.service';
import { AvaliacoesService } from '../services/avaliacoes.service';
import { PedidoService } from '../services/pedido.service';

@Component({
  selector: 'app-restaurante',
  templateUrl: './restaurante.component.html'
})
export class RestauranteComponent implements OnInit {

  cep: string;
  restaurante: any;
  cardapio: any;
  avaliacoes: Array<any> = [];
  pedido: any = {
    itens: []
  };
  itemDoPedidoEscolhido: any;
  adicionandoItemAoPedido = false;
  exibeFormularioDeEntrega = false;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private restaurantesService: RestaurantesService,
              private cardapioService: CardapioService,
              private avaliacoesService: AvaliacoesService,
              private pedidoService: PedidoService) {
  }

  ngOnInit() {
    this.cep = this.route.snapshot.params.cep;
    const restauranteId = this.route.snapshot.params.restauranteId;

    this.restaurantesService
      .porId(this.cep, restauranteId)
      .subscribe(restaurante => {
        this.restaurante = restaurante;
        this.pedido.restaurante = restaurante;
      });

    this.cardapioService
      .porIdDoRestaurante(restauranteId)
      .subscribe(cardapio => this.cardapio = cardapio);

    this.avaliacoesService
      .porIdDoRestaurante(restauranteId)
      .subscribe(avaliacoes => this.avaliacoes = avaliacoes);

  }

  escolheItemDoCardapio(itemDoCardapio) {
    this.itemDoPedidoEscolhido = { itemDoCardapio, quantidade: 1 };
    this.adicionandoItemAoPedido = true;
  }

  adicionaItemAoPedido() {
    if (this.adicionandoItemAoPedido) {
      this.pedido.itens.push(this.itemDoPedidoEscolhido);
    }
    this.itemDoPedidoEscolhido = null;
    this.adicionandoItemAoPedido = false;
  }

  editaItemDoPedido(itemPedido) {
    this.itemDoPedidoEscolhido = itemPedido;
  }

  removeItemDoPedido(itemPedido) {
    this.pedido.itens = this.pedido.itens.filter(i => i !== itemPedido);
    this.itemDoPedidoEscolhido = null;
    this.adicionandoItemAoPedido = false;
  }

  fazPedido() {
    this.pedido.restaurante = this.restaurante;
    this.pedido.entrega = { cep: this.cep, cliente: {} };
    this.exibeFormularioDeEntrega = true;
  }

  registraEntrega() {
    this.pedidoService.adiciona(this.pedido)
    .subscribe(pedido => this.router.navigateByUrl(`pedidos/${pedido.id}/pagamento`));

  }

  calculaSubTotal(itemPedido) {
    const itemCardapio = itemPedido.itemDoCardapio;
    const preco = itemCardapio.precoPromocional || itemCardapio.preco;
    return itemPedido.quantidade * preco;
  }

  totalDoPedido() {
    let total = this.restaurante.taxaDeEntregaEmReais || 0;
    this.pedido.itens.forEach(item => {
      total += this.calculaSubTotal(item);
    });
    return total;
  }
}
