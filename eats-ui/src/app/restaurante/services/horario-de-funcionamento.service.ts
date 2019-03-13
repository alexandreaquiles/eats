import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from 'src/environments/environment';

import { DiaDaSemanaService } from './dia-da-semana.service';

@Injectable()
export class HorarioDeFuncionamentoService {

  private API = environment.baseUrl + '/restaurantes';

  constructor(private http: HttpClient,
              private diaDaSemanaService: DiaDaSemanaService) {
  }

  todosDoRestaurante(restaurante): Observable<any> {
    return this.http.get(`${this.API}/${restaurante.id}/horarios-de-funcionamento`)
      .pipe(
        map(horarios => this.ordena(horarios))
      );
  }

  porId(restaurante, horario) {
    return this.http.get(`${this.API}/${restaurante.id}/horarios-de-funcionamento/${horario.id}`);
  }

  salva(restaurante: any, horario: any): Observable<any> {
    if (horario.id) {
      return this.http.put(`${this.API}/${restaurante.id}/horarios-de-funcionamento/${horario.id}`, horario);
    } else {
      return this.http.post(`${this.API}/${restaurante.id}/horarios-de-funcionamento`, horario);
    }
  }

  remove(restaurante, horarioDeFuncionamento: any) {
    return this.http.delete(`${this.API}/${restaurante.id}/horarios-de-funcionamento/${horarioDeFuncionamento.id}`);
  }

  ordena(horarios) {
    return horarios.sort(
      (a,b) => this.diaDaSemanaService.compara(a.diaDaSemana, b.diaDaSemana)
    );
  }

}
