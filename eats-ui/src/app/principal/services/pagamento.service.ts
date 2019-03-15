import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class PagamentoService {

  private API = environment.baseUrl + '/pagamentos';

  constructor(private http: HttpClient) {
  }

  porId(pedidoId) {
    return this.http.get(`${this.API}/${pedidoId}`);
  }

  cria(pedido): Observable<any> {
    return this.http.post(`${this.API}`, pedido);
  }

  confirma(pedido): Observable<any> {
    return this.http.put(`${this.API}/${pedido.id}`, pedido);
  }

  cancela(pedido): Observable<any> {
    return this.http.delete(`${this.API}/${pedido.id}`, pedido);
  }

}
