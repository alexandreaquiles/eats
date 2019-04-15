import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from '@angular/router';

import { TipoDeCozinhaService } from 'src/app/services/tipo-de-cozinha.service';
import { RestauranteService } from 'src/app/services/restaurante.service';

@Component({
  selector: 'app-lista-restaurantes',
  templateUrl: './lista-restaurantes.component.html'
})
export class ListaRestaurantesComponent implements OnInit {

  tiposDeCozinha: Array<any>;
  cep: string;
  tipoDeCozinhaId: string;
  restaurantesMaisProximos: Array<any>;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private tipoDeCozinhaService: TipoDeCozinhaService,
              private restaurantesService: RestauranteService) {
  }

  ngOnInit() {
    this.tipoDeCozinhaService.todos().subscribe(tipos => {
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
          }
        }
      }
    );
  }

  escolher(restaurante) {
    this.router.navigateByUrl(`/pedidos/${this.cep}/restaurante/${restaurante.id}`);
  }
}
