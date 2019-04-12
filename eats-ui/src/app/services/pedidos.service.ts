import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PedidosService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) {
  }

  porId(pedidoId) {
    return this.http.get(`${this.API}/pedidos/${pedidoId}`);
  }

  adiciona(pedido): Observable<any> {
    return this.http.post(`${this.API}/pedidos`, pedido);
  }

  pendentes(restauranteId): Observable<any> {
    return this.http.get(`${this.API}/parceiros/restaurantes/${restauranteId}/pedidos/pendentes`);
  }

  atualizaStatus(pedido): Observable<any> {
    return this.http.put(`${this.API}/parceiros/pedidos/${pedido.id}/status`, pedido);
  }

}
