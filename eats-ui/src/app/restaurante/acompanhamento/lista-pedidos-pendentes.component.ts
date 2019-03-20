import { Component, OnInit } from '@angular/core';
import { PedidosService } from '../services/pedidos.service';

@Component({
  selector: 'app-lista-pedidos-pendentes',
  templateUrl: './lista-pedidos-pendentes.component.html'
})
export class ListaPedidosPendentesComponent implements OnInit {

  pendentes: Array<any>;

  constructor(private pedidosService: PedidosService) {
  }

  ngOnInit() {
    this.pedidosService.pendentes()
      .subscribe(pedidosPendentes => this.pendentes = pedidosPendentes);
  }

  confirma(pedido) {
    pedido.status = 'CONFIRMADO'
  }

  avisaPronto(pedido) {
    pedido.status = 'PRONTO'
  }

  avisaSaiu(pedido) {
    pedido.status = 'SAIU_PARA_ENTREGA'
  }

  avisaEntregue(pedido) {
    pedido.status = 'ENTREGUE'
  }

}
