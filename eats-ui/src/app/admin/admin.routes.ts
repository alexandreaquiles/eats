import { RouterModule, Routes } from '@angular/router';

import { AdminComponent } from './admin.component';
import { TipoDeCozinhaListagemComponent } from './tipo-de-cozinha/tipo-de-cozinha-listagem.component';
import { TipoDeCozinhaCadastroComponent } from './tipo-de-cozinha/tipo-de-cozinha-cadastro.component';
import { FormaDePagamentoListagemComponent } from './forma-de-pagamento/forma-de-pagamento-listagem.component';
import { FormaDePagamentoCadastroComponent } from './forma-de-pagamento/forma-de-pagamento-cadastro.component';
import { RestauranteEmAprovacaoComponent } from './restaurantes-em-aprovacao/restaurantes-em-aprovacao.component';

const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'admin/tipo-de-cozinha',
    component: TipoDeCozinhaListagemComponent
  },
  {
    path: 'admin/tipo-de-cozinha/cadastro',
    component: TipoDeCozinhaCadastroComponent
  },
  {
    path: 'admin/tipo-de-cozinha/cadastro/:id',
    component: TipoDeCozinhaCadastroComponent
  },
  {
    path: 'admin/forma-de-pagamento',
    component: FormaDePagamentoListagemComponent
  },
  {
    path: 'admin/forma-de-pagamento/cadastro',
    component: FormaDePagamentoCadastroComponent
  },
  {
    path: 'admin/forma-de-pagamento/cadastro/:id',
    component: FormaDePagamentoCadastroComponent
  },
  {
    path: 'admin/restaurantes-em-aprovacao',
    component: RestauranteEmAprovacaoComponent
  }
];

export const adminRoutes = RouterModule.forChild(routes);
