import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class FormaDePagamentoService {

  private ADMIN_API = '//localhost:8080/admin/formas-de-pagamento';

  constructor(private http: HttpClient) {
  }

  all(): Observable<any> {
    return this.http.get(this.ADMIN_API);
  }

  get(id: string) {
    return this.http.get(this.ADMIN_API + '/' + id);
  }

  save(formaDePagamento: any): Observable<any> {
    let result: Observable<Object>;
    if (formaDePagamento.id) {
      result = this.http.put(this.ADMIN_API + '/' + formaDePagamento.id, formaDePagamento);
    } else {
      result = this.http.post(this.ADMIN_API, formaDePagamento);
    }
    return result;
  }

  remove(formaDePagamento: any) {
    return this.http.delete(this.ADMIN_API + '/' + formaDePagamento.id);
  }

}
