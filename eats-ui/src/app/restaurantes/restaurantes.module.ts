import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RestauranteCadastroComponent } from './restaurante-cadastro/restaurante-cadastro.component';
import { HorariosDeFuncionamentoListagemComponent } from './horarios-de-funcionamento/horarios-de-funcionamento-listagem.component';
import { HorariosDeFuncionamentoCadastroComponent } from './horarios-de-funcionamento/horarios-de-funcionamento-cadastro.component';
import { FormasDePagamentoListagemComponent } from './formas-de-pagamento/formas-de-pagamento-listagem.component';
import { CardapioListagemComponent } from './cardapio/cardapio-listagem.component';
import { CategoriaDoCardapioCadastroComponent } from './cardapio/categoria-do-cardapio-cadastro.component';
import { ItemDoCardapioCadastroComponent } from './cardapio/item-do-cardapio-cadastro.component';
import { ListaPedidosPendentesComponent } from './acompanhamento/lista-pedidos-pendentes.component';

import { RestauranteService } from './services/restaurante.service';
import { HorarioDeFuncionamentoService } from './services/horario-de-funcionamento.service';
import { FormaDePagamentoService } from './services/forma-de-pagamento.service';
import { DiaDaSemanaService } from './services/dia-da-semana.service';
import { CardapioService } from './services/cardapio.service';
import { CategoriaDoCardapioService } from './services/categoria-do-cardapio.service';
import { ItemDoCardapioService } from './services/item-do-cardapio.service';
import { PedidosService } from './services/pedidos.service';

import { restauranteRoutes } from './restaurantes.routes';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    RestauranteCadastroComponent,
    HorariosDeFuncionamentoListagemComponent,
    HorariosDeFuncionamentoCadastroComponent,
    FormasDePagamentoListagemComponent,
    CardapioListagemComponent,
    CategoriaDoCardapioCadastroComponent,
    ItemDoCardapioCadastroComponent,
    ListaPedidosPendentesComponent
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
