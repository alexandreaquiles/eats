import { Component, OnInit } from '@angular/core';
import { PedidosService } from '../../services/pedidos.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pedidos-pendentes',
  templateUrl: './pedidos-pendentes.component.html'
})
export class PedidosPendentesComponent implements OnInit {

  pendentes: Array<any>;

  constructor(private route: ActivatedRoute,
              private pedidosService: PedidosService) {
  }

  ngOnInit() {
    const restauranteId = this.route.snapshot.params.restauranteId;
    this.pedidosService.pendentes(restauranteId)
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
