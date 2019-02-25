import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AdminComponent } from './admin.component';
import { TipoDeCozinhaListagemComponent } from './tipo-de-cozinha/tipo-de-cozinha-listagem.component';
import { TipoDeCozinhaEdicaoComponent } from './tipo-de-cozinha/tipo-de-cozinha-edicao.component';
import { FormaDePagamentoListagemComponent } from './forma-de-pagamento/forma-de-pagamento-listagem.component';
import { FormaDePagamentoEdicaoComponent } from './forma-de-pagamento/forma-de-pagamento-edicao.component';

import { TipoDeCozinhaService } from './services/tipo-de-cozinha.service';
import { FormaDePagamentoService } from './services/forma-de-pagamento.service';

import { adminRoutes } from './admin.routes';

@NgModule({
  declarations: [
    AdminComponent,
    TipoDeCozinhaListagemComponent,
    TipoDeCozinhaEdicaoComponent,
    FormaDePagamentoListagemComponent,
    FormaDePagamentoEdicaoComponent
  ],
  imports: [CommonModule, FormsModule, adminRoutes],
  exports: [AdminComponent],
  providers: [TipoDeCozinhaService, FormaDePagamentoService]
})
export class AdminModule { }
