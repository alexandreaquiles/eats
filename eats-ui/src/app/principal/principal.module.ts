import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PrincipalComponent } from './principal.component';
import { ListaRestaurantesComponent } from './lista-restaurantes/lista-restaurantes.component';
import { RestauranteComponent } from './restaurante/restaurante.component';
import { PagamentoPedidoComponent } from './pedido/pagamento-pedido.component';
import { ResumoPedidoComponent } from './resumo/resumo-pedido.component';
import { StatusPedidoComponent } from './status/status-pedido.component';

import { principalRoutes } from './principal.routes';

import { RestaurantesService } from './services/restaurantes.service';
import { CardapioService } from './services/cardapio.service';
import { AvaliacoesService } from './services/avaliacoes.service';
import { PedidoService } from './services/pedido.service';
import { PagamentoService } from './services/pagamento.service';
import { RecomendacoesService } from './services/recomendacoes.service';

@NgModule({
  declarations: [
    PrincipalComponent,
    ListaRestaurantesComponent,
    RestauranteComponent,
    PagamentoPedidoComponent,
    ResumoPedidoComponent,
    StatusPedidoComponent
  ],
  imports: [ CommonModule, FormsModule, principalRoutes ],
  providers: [
    RestaurantesService,
    CardapioService,
    AvaliacoesService,
    PedidoService,
    PagamentoService,
    RecomendacoesService
  ]
})
export class PrincipalModule { }
