import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class CardapioService {

  private API = environment.baseUrl + '/restaurantes';

  constructor(private http: HttpClient) {
  }

  porId(restauranteId, cardapioId): Observable<any> {
    return this.http.get(`${this.API}/${restauranteId}/cardapio/${cardapioId}`);
  }

  doRestaurante(restaurante): Observable<any> {
    return this.http.get(`${this.API}/${restaurante.id}/cardapio`);
  }

}
