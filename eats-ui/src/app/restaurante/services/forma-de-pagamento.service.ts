import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class FormaDePagamentoService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) {
  }

  doRestaurante(restaurante) {
    return this.http.get(`${this.API}/restaurantes/${restaurante.id}/formas-de-pagamento`);
  }

  todas(): Observable<any> {
    return this.http.get(`${this.API}/formas-de-pagamento`);
  }

  adicionaAoRestaurante(formaDePagamento): Observable<any> {
    return this.http.post(`${this.API}/restaurantes/${formaDePagamento.restaurante.id}/formas-de-pagamento`, formaDePagamento);
  }

  remove(formaDePagamento) {
    return this.http.delete(`${this.API}/restaurantes/${formaDePagamento.restaurante.id}/formas-de-pagamento/${formaDePagamento.id}`);
  }

}
