import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class TiposDeCozinhaService {

  private ADMIN_API = environment.baseUrl + '/admin/tipos-de-cozinha';

  constructor(private http: HttpClient) {
  }

  todos(): Observable<any> {
    return this.http.get(this.ADMIN_API);
  }

}
