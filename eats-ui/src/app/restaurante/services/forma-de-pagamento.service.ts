import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class FormaDePagamentoService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) {
  }

  porIdDoRestaurante(restauranteId) {
    return this.http.get(`${this.API}/restaurantes/${restauranteId}/formas-de-pagamento`);
  }

  todas(): Observable<any> {
    return this.http.get(`${this.API}/formas-de-pagamento`);
  }

}
