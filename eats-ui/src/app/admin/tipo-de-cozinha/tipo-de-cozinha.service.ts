import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TipoDeCozinhaService {

  private ADMIN_API = '//localhost:8080/admin/tipos-de-cozinha';

  constructor(private http: HttpClient) {
  }

  todos(): Observable<any> {
    return this.http.get(this.ADMIN_API);
  }

  porId(id: string) {
    return this.http.get(this.ADMIN_API + '/' + id);
  }

  salva(tipoDeCozinha: any): Observable<any> {
    let result: Observable<Object>;
    if (tipoDeCozinha.id) {
      result = this.http.put(this.ADMIN_API + '/' + tipoDeCozinha.id, tipoDeCozinha);
    } else {
      result = this.http.post(this.ADMIN_API, tipoDeCozinha);
    }
    return result;
  }

  remove(tipoDeCozinha: any) {
    return this.http.delete(this.ADMIN_API + '/' + tipoDeCozinha.id);
  }

}
