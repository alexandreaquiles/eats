import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PedidoService } from '../services/pedido.service';

import * as StompJS from '@stomp/stompjs';
import SockJS from 'sockjs-client';

@Component({
  selector: 'app-status-pedido',
  templateUrl: './status-pedido.component.html'
})
export class StatusPedidoComponent implements OnInit, OnDestroy {

  pedido: any = {};
  stompClient

  constructor(private route: ActivatedRoute,
              private router: Router,
              private pedidoService: PedidoService) {
  }

  ngOnInit() {
    const pedidoId = this.route.snapshot.params.pedidoId;
    this.pedidoService.porId(pedidoId)
      .subscribe(pedido => this.pedido = pedido);

    const ws = new SockJS(`http://localhost:8080/socket`);
    this.stompClient = StompJS.Stomp.over(ws);
    this.stompClient.connect({}, () => {
      this.stompClient.subscribe('/pedidos/status', message => {
        const pedido = JSON.parse(message.body);
        this.pedido.status = pedido.status;
      });
    });
  }

  ngOnDestroy() {
    this.stompClient.disconnect();
  }

}
