import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class RestauranteService {

  private API = environment.baseUrl + '/restaurantes';

  constructor(private http: HttpClient) {
  }

  porId(id: string) {
    return this.http.get(this.API + '/' + id);
  }

  salva(restaurante): Observable<any> {
    let result: Observable<Object>;
    if (restaurante.id) {
      result = this.http.put(this.API + '/' + restaurante.id, restaurante);
    } else {
      result = this.http.post(this.API, restaurante);
    }
    return result;
  }

}
