import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class TipoDeCozinhaService {

  private ADMIN_API = environment.baseUrl + '/admin/tipos-de-cozinha';

  constructor(private http: HttpClient) {
  }

  todos(): Observable<any> {
    return this.http.get(this.ADMIN_API);
  }

  porId(id: string) {
    return this.http.get(this.ADMIN_API + '/' + id);
  }

  salva(tipoDeCozinha: any): Observable<any> {
    if (tipoDeCozinha.id) {
     return this.http.put(this.ADMIN_API + '/' + tipoDeCozinha.id, tipoDeCozinha);
    }
    return this.http.post(this.ADMIN_API, tipoDeCozinha);
  }

  remove(tipoDeCozinha: any) {
    return this.http.delete(this.ADMIN_API + '/' + tipoDeCozinha.id);
  }

}
