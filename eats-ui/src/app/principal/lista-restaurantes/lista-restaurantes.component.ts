import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from '@angular/router';

import { TiposDeCozinhaService } from '../services/tipos-de-cozinha.service';
import { RestaurantesService } from '../services/restaurantes.service';
import { RecomendacoesService } from '../services/recomendacoes.service';

@Component({
  selector: 'app-lista-restaurantes',
  templateUrl: './lista-restaurantes.component.html'
})
export class ListaRestaurantesComponent implements OnInit {

  tiposDeCozinha: Array<any>;
  cep: string;
  tipoDeCozinhaId: string;
  restaurantesMaisProximos: Array<any>;
  recomendacoes: Array<any>;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private tiposDeCozinhaService: TiposDeCozinhaService,
              private restaurantesService: RestaurantesService,
              private recomendacoesService: RecomendacoesService) {
  }

  ngOnInit() {
    this.tiposDeCozinhaService.todos().subscribe(tipos => {
      this.tiposDeCozinha = tipos;
    });

    this.route.params.subscribe(
      params => {
        this.cep = params.cep;
        if (this.cep) {

          this.tipoDeCozinhaId = params.tipoDeCozinhaId;
          if (this.tipoDeCozinhaId) {
            this.restaurantesService.maisProximosPorCepETipoDeCozinha(this.cep, this.tipoDeCozinhaId)
              .subscribe(restaurantes => this.restaurantesMaisProximos = restaurantes);
          } else {
            this.restaurantesService.maisProximosPorCep(this.cep)
              .subscribe(restaurantes => this.restaurantesMaisProximos = restaurantes);

            this.recomendacoesService.paraOCep(this.cep)
              .subscribe(recomendacoes => this.recomendacoes = recomendacoes);
          }
        }
      }
    );
  }

  escolher(restaurante) {
    this.router.navigateByUrl(`/restaurantes/${this.cep}/restaurante/${restaurante.id}`);
  }
}
