import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { RestaurantesService } from '../services/restaurantes.service';
import { PedidoService } from '../services/pedido.service';
import { PagamentoService } from '../services/pagamento.service';

@Component({
  selector: 'app-pagamento-pedido',
  templateUrl: './pagamento-pedido.component.html'
})
export class PagamentoPedidoComponent implements OnInit {

  pedido: any;
  formasDePagamento: Array<any>;
  pagamento: any = {};

  constructor(private route: ActivatedRoute,
              private pagamentoService: PagamentoService,
              private pedidoService: PedidoService,
              private restaurantesService: RestaurantesService) {
  }

  ngOnInit() {
    const pedidoId = this.route.snapshot.params.pedidoId;
    this.pedidoService.porId(pedidoId)
      .subscribe((pedido: any) => {
        this.pedido = pedido;
        this.pagamento = { pedido, valor: pedido.total };
        this.restaurantesService.formasDePagamento(pedido.restaurante)
          .subscribe(formasDePagamento => this.formasDePagamento = formasDePagamento);
      });
  }

  criaPagamento() {
    this.pagamentoService.cria(this.pagamento)
      .subscribe(pagamento => {
        this.pagamento.id = pagamento.id;
        this.pagamento.status = pagamento.status;
      });
  }

  confirmaPagamento() {
    this.pagamentoService.confirma(this.pagamento)
      .subscribe();
  }

  cancelaPagamento() {
    this.pagamentoService.cancela(this.pagamento)
      .subscribe();
  }

}
