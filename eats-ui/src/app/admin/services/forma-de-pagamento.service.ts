import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable()
export class FormaDePagamentoService {

  private ADMIN_API = environment.baseUrl + '/admin/formas-de-pagamento';

  constructor(private http: HttpClient) {
  }

  todas(): Observable<any> {
    return this.http.get(this.ADMIN_API);
  }

  salva(formaDePagamento: any): Observable<any> {
    if (formaDePagamento.id) {
      return this.http.put(this.ADMIN_API + '/' + formaDePagamento.id, formaDePagamento);
    }
    return this.http.post(this.ADMIN_API, formaDePagamento);
  }

  remove(formaDePagamento: any) {
    return this.http.delete(this.ADMIN_API + '/' + formaDePagamento.id);
  }

  tipos(): Observable<any> {
    return this.http.get(this.ADMIN_API+'/tipos');
  }

}
