import { RouterModule, Routes } from '@angular/router';

import { TiposDeCozinhaComponent } from './tipo-de-cozinha/tipos-de-cozinha.component';
import { FormaDePagamentoListagemComponent } from './forma-de-pagamento/forma-de-pagamento-listagem.component';
import { FormaDePagamentoCadastroComponent } from './forma-de-pagamento/forma-de-pagamento-cadastro.component';
import { RestauranteEmAprovacaoComponent } from './restaurantes-em-aprovacao/restaurantes-em-aprovacao.component';

const routes: Routes = [
  {
    path: 'admin/tipos-de-cozinha',
    component: TiposDeCozinhaComponent
  },
  {
    path: 'admin/formas-de-pagamento',
    component: FormaDePagamentoListagemComponent
  },
  {
    path: 'admin/formas-de-pagamento/cadastro',
    component: FormaDePagamentoCadastroComponent
  },
  {
    path: 'admin/formas-de-pagamento/cadastro/:id',
    component: FormaDePagamentoCadastroComponent
  },
  {
    path: 'admin/restaurantes-em-aprovacao',
    component: RestauranteEmAprovacaoComponent
  }
];

export const adminRoutes = RouterModule.forChild(routes);
