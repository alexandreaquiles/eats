import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AdminComponent } from './admin.component';
import { TipoDeCozinhaListagemComponent } from './tipo-de-cozinha/tipo-de-cozinha-listagem.component';
import { TipoDeCozinhaCadastroComponent } from './tipo-de-cozinha/tipo-de-cozinha-cadastro.component';
import { FormaDePagamentoListagemComponent } from './forma-de-pagamento/forma-de-pagamento-listagem.component';
import { FormaDePagamentoCadastroComponent } from './forma-de-pagamento/forma-de-pagamento-cadastro.component';

import { TipoDeCozinhaService } from './services/tipo-de-cozinha.service';
import { FormaDePagamentoService } from './services/forma-de-pagamento.service';

import { adminRoutes } from './admin.routes';

@NgModule({
  declarations: [
    AdminComponent,
    TipoDeCozinhaListagemComponent,
    TipoDeCozinhaCadastroComponent,
    FormaDePagamentoListagemComponent,
    FormaDePagamentoCadastroComponent
  ],
  imports: [CommonModule, FormsModule, adminRoutes],
  providers: [TipoDeCozinhaService, FormaDePagamentoService]
})
export class AdminModule { }
