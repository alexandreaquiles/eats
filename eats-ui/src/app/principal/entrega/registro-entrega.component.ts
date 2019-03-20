import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PedidoService } from '../services/pedido.service';
import { EntregaService } from '../services/entrega.service';

@Component({
  selector: 'app-registro-entrega',
  templateUrl: './registro-entrega.component.html'
})
export class RegistroEntregaComponent implements OnInit {

  pedido: any = {};
  entrega: any = {};

  constructor(private route: ActivatedRoute,
              private router: Router,
              private pedidoService: PedidoService,
              private entregaService: EntregaService) {
  }

  ngOnInit() {
    const pedidoId = this.route.snapshot.params.pedidoId;
    this.pedidoService.porId(pedidoId)
      .subscribe((pedido: any) => {
        this.pedido = pedido;
        this.entrega = pedido.entrega;
      });
  }

  registraEntrega() {
    this.entrega.pedido = { id: this.pedido.id };
    this.entregaService.atualiza(this.entrega)
      .subscribe(() => {
        this.router.navigateByUrl(`pedidos/${this.pedido.id}/status`);
      });
  }

}
