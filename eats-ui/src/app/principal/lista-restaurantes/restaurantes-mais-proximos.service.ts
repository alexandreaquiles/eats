import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class RestaurantesMaisProximosService {

  private API = environment.baseUrl + '/restaurantes/mais-proximos';

  constructor(private http: HttpClient) {
  }

  porCep(cep: string): Observable<any> {
    return this.http.get(this.API+'/'+cep);
  }

}
