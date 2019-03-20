import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class PedidosService {

  private API = environment.baseUrl + '/pedidos';

  constructor(private http: HttpClient) {
  }

  pendentes(): Observable<any> {
    return this.http.get(`${this.API}/pendentes`);
  }
}
