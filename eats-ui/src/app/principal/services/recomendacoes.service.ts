import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class RecomendacoesService {

  private API = environment.baseUrl + '/recomendacoes';

  constructor(private http: HttpClient) {
  }

  paraOCep(cep): Observable<any> {
    return this.http.get(`${this.API}/${cep}`);
  }

}
