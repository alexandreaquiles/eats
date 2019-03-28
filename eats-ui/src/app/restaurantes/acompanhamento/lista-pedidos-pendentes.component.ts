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
    pedido.status = 'CONFIRMADO';
    this.pedidosService.atualizaStatus(pedido)
      .subscribe();
  }

  avisaPronto(pedido) {
    pedido.status = 'PRONTO';
    this.pedidosService.atualizaStatus(pedido)
      .subscribe();
  }

  avisaSaiu(pedido) {
    pedido.status = 'SAIU_PARA_ENTREGA';
    this.pedidosService.atualizaStatus(pedido)
      .subscribe();
  }

  avisaEntregue(pedido) {
    pedido.status = 'ENTREGUE';
    this.pedidosService.atualizaStatus(pedido)
      .subscribe();
  }

}
