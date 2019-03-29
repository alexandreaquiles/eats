import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { TiposDeCozinhaComponent } from './tipos-de-cozinha/tipos-de-cozinha.component';
import { FormasDePagamentoComponent } from './formas-de-pagamento/formas-de-pagamento.component';
import { RestauranteEmAprovacaoComponent } from './restaurantes-em-aprovacao/restaurantes-em-aprovacao.component';
import { DetalhesDoRestauranteComponent } from './detalhes-do-restaurante/detalhes-do-restaurante.component';

import { TipoDeCozinhaService } from '../services/tipo-de-cozinha.service';
import { FormaDePagamentoService } from '../services/forma-de-pagamento.service';
import { RestaurantesService } from './services/restaurantes.service';

import { adminRoutes } from './admin.routes';

@NgModule({
  declarations: [
    TiposDeCozinhaComponent,
    FormasDePagamentoComponent,
    RestauranteEmAprovacaoComponent,
    DetalhesDoRestauranteComponent
  ],
  imports: [CommonModule, FormsModule, adminRoutes],
  providers: [TipoDeCozinhaService, FormaDePagamentoService, RestaurantesService ]
})
export class AdminModule { }
