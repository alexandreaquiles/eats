import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from 'src/environments/environment';

@Injectable()
export class RestaurantesService {

  private API = environment.baseUrl;
  private ADMIN_API = environment.baseUrl + '/admin/restaurantes';

  diasDaSemana = [
    { nome: 'Segunda', valor: 'MONDAY', ordem: 1},
    { nome: 'Terça', valor: 'TUESDAY', ordem: 2},
    { nome: 'Quarta', valor: 'WEDNESDAY', ordem: 3},
    { nome: 'Quinta', valor: 'THURSDAY', ordem: 4},
    { nome: 'Sexta', valor: 'FRIDAY', ordem: 5},
    { nome: 'Sábado', valor: 'SATURDAY', ordem: 6},
    { nome: 'Domingo', valor: 'SUNDAY', ordem: 7},
  ];

  constructor(private http: HttpClient) {
  }

  emAprovacao(): Observable<any> {
    return this.http.get(`${this.ADMIN_API}/em-aprovacao`)
  }

  aprova(restaurante): Observable<any> {
    return this.http.patch(`${this.ADMIN_API}/${restaurante.id}`, restaurante);
  }

  formasDePagamentoDoRestaurante(restaurante) {
    return this.http.get(`${this.API}/restaurantes/${restaurante.id}/formas-de-pagamento`);
  }

  horariosDeFuncionamentoDoRestaurante(restaurante): Observable<any> {
    return this.http.get(`${this.API}/restaurantes/${restaurante.id}/horarios-de-funcionamento`)
      .pipe(
        map((horarios:any) => {
          return horarios.map(horario => {
            const diaDaSemana = this.diasDaSemana.find(d => d.valor == horario.diaDaSemana);
            horario.diaDaSemana = diaDaSemana.nome;
            return horario;
          });
        })
      );
  }

  cardapioDoRestaurante(restaurante): Observable<any> {
    return this.http.get(`${this.API}/restaurantes/${restaurante.id}/cardapio`);
  }

}
