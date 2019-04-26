import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PagamentoService {

  private API = environment.baseUrl + '/pagamentos';

  constructor(private http: HttpClient) {
  }

  cria(pagamento): Observable<any> {
    this.ajustaIds(pagamento);
    return this.http.post(`${this.API}`, pagamento);
  }

  confirma(pagamento): Observable<any> {
    const url = pagamento._links.confirma.href;
    const method = pagamento._templates.default.method;
    return this.http.request(method, url, { body: pagamento });
  }

  cancela(pagamento): Observable<any> {
    const url = pagamento._links.cancela.href;
    const method = pagamento._templates.cancela.method;
    return this.http.request(method, url, { body: pagamento });
  }

  private ajustaIds(pagamento) {
    pagamento.formaDePagamentoId = pagamento.formaDePagamento.id;
    pagamento.pedidoId = pagamento.pedido.id;
  }
}
