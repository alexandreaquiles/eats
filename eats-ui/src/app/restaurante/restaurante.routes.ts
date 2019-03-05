import { RouterModule, Routes } from '@angular/router';

import { RestauranteCadastroComponent } from './restaurante-cadastro/restaurante-cadastro.component';
import { HorariosDeFuncionamentoListagemComponent } from './horarios-de-funcionamento/horarios-de-funcionamento-listagem.component';
import { HorariosDeFuncionamentoCadastroComponent } from './horarios-de-funcionamento/horarios-de-funcionamento-cadastro.component';
import { FormasDePagamentoListagemComponent } from './formas-de-pagamento/formas-de-pagamento-listagem.component';

const routes: Routes = [
  {
    path: 'cadastro/restaurante',
    component: RestauranteCadastroComponent
  },
  {
    path: 'cadastro/restaurante/:id',
    component: RestauranteCadastroComponent
  },
  {
    path: 'cadastro/restaurante/:restauranteId/horarios-de-funcionamento',
    component: HorariosDeFuncionamentoListagemComponent
  },
  {
    path: 'cadastro/restaurante/:restauranteId/horarios-de-funcionamento/cadastro',
    component: HorariosDeFuncionamentoCadastroComponent
  },
  {
    path: 'cadastro/restaurante/:restauranteId/horarios-de-funcionamento/cadastro/:horarioId',
    component: HorariosDeFuncionamentoCadastroComponent
  },
  {
    path: 'cadastro/restaurante/:restauranteId/formas-de-pagamento',
    component: FormasDePagamentoListagemComponent
  }
];

export const restauranteRoutes = RouterModule.forChild(routes);

