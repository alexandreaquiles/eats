import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class PedidoService {

  private API = environment.baseUrl + '/pedidos';

  constructor(private http: HttpClient) {
  }

  porId(pedidoId) {
    return this.http.get(`${this.API}/${pedidoId}`);
  }

  adiciona(pedido): Observable<any> {
    return this.http.post(`${this.API}`, pedido);
  }

}
