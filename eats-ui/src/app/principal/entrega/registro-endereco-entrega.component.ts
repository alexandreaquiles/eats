import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PedidoService } from '../services/pedido.service';

@Component({
  selector: 'app-registro-entrega',
  templateUrl: './registro-endereco-entrega.component.html'
})
export class RegistroEnderecoEntregaComponent implements OnInit {

  pedido: any = {};
  entrega: any = {};

  constructor(private route: ActivatedRoute,
              private router: Router,
              private pedidoService: PedidoService) {
  }

  ngOnInit() {
    const pedidoId = this.route.snapshot.params.pedidoId;
    this.pedidoService.porId(pedidoId)
      .subscribe((pedido: any) => {
        this.pedido = pedido;
        this.entrega = pedido.entrega;
      });
  }

  registraEnderecoDeEntrega() {
    console.log(this.entrega);
    //TODO: terminar
  }

}
