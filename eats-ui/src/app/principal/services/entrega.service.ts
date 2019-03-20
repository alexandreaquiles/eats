import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class EnderecoDeEntregaService {

  private API = environment.baseUrl + '/entregas';

  constructor(private http: HttpClient) {
  }

  atualiza(endereco) {
    return this.http.put(`${this.API}/${endereco.id}`, endereco);
  }

}
