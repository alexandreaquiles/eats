import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class RestauranteService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) {
  }

  porId(id: string): Observable<any> {
    return this.http.get(`${this.API}/restaurantes/${id}`);
  }

  salva(restaurante): Observable<any> {
    if (restaurante.id) {
      return this.http.put(`${this.API}/parceiros/restaurantes/${restaurante.id}`, restaurante);
    }
    return this.http.post(`${this.API}/parceiros/restaurantes`, restaurante);
  }

  emAprovacao(): Observable<any> {
    return this.http.get(`${this.API}/admin/restaurantes/em-aprovacao`);
  }

  aprova(restaurante): Observable<any> {
    return this.http.patch(`${this.API}/admin/restaurantes/${restaurante.id}`, restaurante);
  }

}
