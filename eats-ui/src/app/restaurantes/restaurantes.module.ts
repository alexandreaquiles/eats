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

import { restauranteRoutes } from './restaurantes.routes';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PipesModule } from '../pipes/pipes.module';

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
  imports: [ CommonModule, FormsModule, NgbModule, restauranteRoutes, PipesModule ]

})
export class RestaurantesModule { }
