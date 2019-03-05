import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RestauranteCadastroComponent } from './restaurante-cadastro/restaurante-cadastro.component';
import { HorariosDeFuncionamentoListagemComponent } from './horarios-de-funcionamento/horarios-de-funcionamento-listagem.component';
import { HorariosDeFuncionamentoCadastroComponent } from './horarios-de-funcionamento/horarios-de-funcionamento-cadastro.component';
import { FormasDePagamentoListagemComponent } from './formas-de-pagamento/formas-de-pagamento-listagem.component';

import { RestauranteService } from './services/restaurante.service';
import { HorarioDeFuncionamentoService } from './services/horario-de-funcionamento.service';
import { FormaDePagamentoService } from './services/forma-de-pagamento.service';
import { DiaDaSemanaService } from './services/dia-da-semana.service';

import { restauranteRoutes } from './restaurante.routes';

@NgModule({
  declarations: [
    RestauranteCadastroComponent,
    HorariosDeFuncionamentoListagemComponent,
    HorariosDeFuncionamentoCadastroComponent,
    FormasDePagamentoListagemComponent
  ],
  imports: [ CommonModule, FormsModule, restauranteRoutes ],
  providers: [ RestauranteService, HorarioDeFuncionamentoService, FormaDePagamentoService, DiaDaSemanaService ]
})
export class RestauranteModule { }
