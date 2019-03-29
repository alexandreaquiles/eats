import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class PedidosService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) {
  }

  pendentes(): Observable<any> {
    return this.http.get(`${this.API}/pedidos/pendentes`);
  }

  atualizaStatus(pedido): Observable<any> {
    return this.http.put(`${this.API}/pedidos/${pedido.id}/status`, pedido);
  }

}
