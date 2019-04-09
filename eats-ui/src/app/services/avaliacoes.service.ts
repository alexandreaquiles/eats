import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AvaliacoesService {

  private API = environment.baseUrl + '/restaurantes';

  constructor(private http: HttpClient) {
  }

  porIdDoRestaurante(restauranteId: string): Observable<any> {
    return this.http.get(`${this.API}/${restauranteId}/avaliacoes`);
  }

  salva(avaliacao: any) {
    const restauranteId = avaliacao.pedido.restaurante.id;
    return this.http.post(`${this.API}/${restauranteId}/avaliacoes`, avaliacao);
  }
}
