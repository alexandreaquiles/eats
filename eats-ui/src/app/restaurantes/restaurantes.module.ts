import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RestauranteCadastroComponent } from './restaurante-cadastro/restaurante-cadastro.component';
import { HorariosDeFuncionamentoComponent } from './horarios-de-funcionamento/horarios-de-funcionamento.component';
import { FormasDePagamentoComponent } from './formas-de-pagamento/formas-de-pagamento.component';
import { CardapioListagemComponent } from './cardapio/cardapio-listagem.component';
import { CategoriaDoCardapioCadastroComponent } from './cardapio/categoria-do-cardapio-cadastro.component';
import { ItemDoCardapioCadastroComponent } from './cardapio/item-do-cardapio-cadastro.component';
import { PedidosPendentesComponent } from './pedidos-pendentes/pedidos-pendentes.component';

import { FormaDePagamentoService } from 'src/app/services/forma-de-pagamento.service';
import { RestauranteService } from '../services/restaurante.service';
import { HorarioDeFuncionamentoService } from '../services/horario-de-funcionamento.service';
import { DiaDaSemanaService } from '../services/dia-da-semana.service';
import { CardapioService } from '../services/cardapio.service';
import { CategoriaDoCardapioService } from '../services/categoria-do-cardapio.service';
import { ItemDoCardapioService } from '../services/item-do-cardapio.service';
import { PedidosService } from '../services/pedidos.service';

import { restauranteRoutes } from './restaurantes.routes';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    RestauranteCadastroComponent,
    HorariosDeFuncionamentoComponent,
    FormasDePagamentoComponent,
    CardapioListagemComponent,
    CategoriaDoCardapioCadastroComponent,
    ItemDoCardapioCadastroComponent,
    PedidosPendentesComponent
  ],
  imports: [ CommonModule, FormsModule, NgbModule, restauranteRoutes ],
  providers: [
    RestauranteService,
    HorarioDeFuncionamentoService,
    FormaDePagamentoService,
    DiaDaSemanaService,
    CardapioService,
    CategoriaDoCardapioService,
    ItemDoCardapioService,
    PedidosService
  ]
})
export class RestaurantesModule { }
