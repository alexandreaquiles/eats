import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PrincipalComponent } from './principal/principal.component';

import { AdminComponent } from './admin/admin.component';
import { TipoDeCozinhaListagemComponent } from './admin/tipo-de-cozinha/tipo-de-cozinha-listagem.component';
import { TipoDeCozinhaEdicaoComponent } from './admin/tipo-de-cozinha/tipo-de-cozinha-edicao.component';
import { FormaDePagamentoListagemComponent } from './admin/forma-de-pagamento/forma-de-pagamento-listagem.component';
import { FormaDePagamentoEdicaoComponent } from './admin/forma-de-pagamento/forma-de-pagamento-edicao.component';

const routes: Routes = [
  {
    path: '',
    component: PrincipalComponent
  },
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
    path: 'admin/forma-de-pagamento/cadastro',
    component: FormaDePagamentoEdicaoComponent
  },
  {
    path: 'admin/forma-de-pagamento/cadastro/:id',
    component: FormaDePagamentoEdicaoComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
