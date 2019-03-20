import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class EntregaService {

  private API = environment.baseUrl + '/entregas';

  constructor(private http: HttpClient) {
  }

  atualiza(entrega): Observable<any> {
    return this.http.put(`${this.API}/${entrega.id}`, entrega);
  }

}
