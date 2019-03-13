import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class CategoriaDoCardapioService {

  private API = environment.baseUrl + '/restaurantes';

  constructor(private http: HttpClient) {
  }

  porId(restauranteId, cardapioId, categoriaId): Observable<any> {
    return this.http.get(`${this.API}/${restauranteId}/cardapio/${cardapioId}/categoria/${categoriaId}`);
  }

  adicionaCategoriaAoCardapio(restaurante, cardapio, categoria): Observable<any> {
    return this.http.post(`${this.API}/${restaurante.id}/cardapio/${cardapio.id}/categoria`, categoria);
  }

  remove(restaurante, cardapio, categoria, item): Observable<any> {
    return this.http.delete(`${this.API}/${restaurante.id}/cardapio/${cardapio.id}/categoria/${categoria.id}/item/${item.id}`);
  }

}
