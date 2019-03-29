import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class CategoriaDoCardapioService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) {
  }

  porId(restauranteId, cardapioId, categoriaId): Observable<any> {
    return this.http.get(`${this.API}/restaurantes/${restauranteId}/cardapio/${cardapioId}/categoria/${categoriaId}`);
  }

  adicionaCategoriaAoCardapio(categoria): Observable<any> {
    const cardapio = categoria.cardapio;
    const restaurante = cardapio.restaurante;
    return this.http.post(`${this.API}/restaurantes/${restaurante.id}/cardapio/${cardapio.id}/categoria`, categoria);
  }

  remove(item): Observable<any> {
    const categoria = item.categoria;
    const cardapio = categoria.cardapio;
    const restaurante = cardapio.restaurante;
    return this.http.delete(`${this.API}/restaurantes/${restaurante.id}/cardapio/${cardapio.id}/categoria/${categoria.id}/item/${item.id}`);
  }

}
