import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class RestaurantesService {

  private API = environment.baseUrl + '/restaurantes';

  constructor(private http: HttpClient) {
  }

  maisProximosPorCep(cep: string): Observable<any> {
    return this.http.get(this.API+'/mais-proximos/'+cep);
  }

  porId(cep: string, restauranteId: string): Observable<any> {
    return this.http.get(this.API+'/'+cep+'/restaurante/'+restauranteId);
  }

}
