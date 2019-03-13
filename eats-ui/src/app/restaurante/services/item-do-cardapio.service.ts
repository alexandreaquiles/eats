import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class ItemDoCardapioService {

  private API = environment.baseUrl + '/restaurantes';

  constructor(private http: HttpClient) {
  }

  porId(restauranteId, cardapioId, categoriaId, itemId): Observable<any> {
    return this.http.get(`${this.API}/${restauranteId}/cardapio/${cardapioId}/categoria/${categoriaId}/item/${itemId}`);
  }

  salva(restaurante, cardapio, categoria, item): Observable<any> {
    if (item.id) {
      return this.http.put(`${this.API}/${restaurante.id}/cardapio/${cardapio.id}/categoria/${categoria.id}/item/${item.id}`, item);
    }
    return this.http.post(`${this.API}/${restaurante.id}/cardapio/${cardapio.id}/categoria/${categoria.id}/item`, item);
  }


}
