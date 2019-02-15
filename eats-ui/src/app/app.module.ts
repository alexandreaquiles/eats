import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';
import { MatGridListModule } from '@angular/material/grid-list';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import  { AdminComponent } from './admin/admin.component';
import { TipoDeCozinhaListagemComponent } from './admin/tipo-de-cozinha/tipo-de-cozinha-listagem.component';
import { TipoDeCozinhaEdicaoComponent } from './admin/tipo-de-cozinha/tipo-de-cozinha-edicao.component';
import { PrincipalComponent } from './principal/principal.component';
import { FormaDePagamentoListagemComponent } from './admin/forma-de-pagamento/forma-de-pagamento-listagem.component';
import { FormaDePagamentoEdicaoComponent } from './admin/forma-de-pagamento/forma-de-pagamento-edicao.component';

@NgModule({
  declarations: [
    AppComponent,
    PrincipalComponent,
    AdminComponent,
    TipoDeCozinhaListagemComponent,
    TipoDeCozinhaEdicaoComponent,
    FormaDePagamentoListagemComponent,
    FormaDePagamentoEdicaoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatGridListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
