import { RouterModule, Routes } from '@angular/router';

import { AdminComponent } from './admin.component';
import { TipoDeCozinhaListagemComponent } from './tipo-de-cozinha/tipo-de-cozinha-listagem.component';
import { TipoDeCozinhaEdicaoComponent } from './tipo-de-cozinha/tipo-de-cozinha-edicao.component';
import { FormaDePagamentoListagemComponent } from './forma-de-pagamento/forma-de-pagamento-listagem.component';
import { FormaDePagamentoEdicaoComponent } from './forma-de-pagamento/forma-de-pagamento-edicao.component';

const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent
  },
  {
    path: 'admin/tipo-de-cozinha/listar',
    component: TipoDeCozinhaListagemComponent
  },
  {
    path: 'admin/tipo-de-cozinha/adicionar',
    component: TipoDeCozinhaEdicaoComponent
  },
  {
    path: 'admin/tipo-de-cozinha/editar/:id',
    component: TipoDeCozinhaEdicaoComponent
  },
  {
    path: 'admin/forma-de-pagamento/listar',
    component: FormaDePagamentoListagemComponent
  },
  {
    path: 'admin/forma-de-pagamento/adicionar',
    component: FormaDePagamentoEdicaoComponent
  },
  {
    path: 'admin/forma-de-pagamento/editar/:id',
    component: FormaDePagamentoEdicaoComponent
  }
];

export const adminRoutes = RouterModule.forChild(routes);
