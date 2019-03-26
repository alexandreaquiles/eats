import { RouterModule, Routes } from '@angular/router';

import { TiposDeCozinhaComponent } from './tipos-de-cozinha/tipos-de-cozinha.component';
import { FormasDePagamentoComponent } from './formas-de-pagamento/formas-de-pagamento.component';
import { RestauranteEmAprovacaoComponent } from './restaurantes-em-aprovacao/restaurantes-em-aprovacao.component';

const routes: Routes = [
  {
    path: 'admin/tipos-de-cozinha',
    component: TiposDeCozinhaComponent
  },
  {
    path: 'admin/formas-de-pagamento',
    component: FormasDePagamentoComponent
  },
  {
    path: 'admin/restaurantes-em-aprovacao',
    component: RestauranteEmAprovacaoComponent
  }
];

export const adminRoutes = RouterModule.forChild(routes);
