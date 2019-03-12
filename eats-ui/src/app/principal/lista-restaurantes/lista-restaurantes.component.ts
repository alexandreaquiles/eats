import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from '@angular/router';

import { TiposDeCozinhaService } from '../services/tipos-de-cozinha.service';
import { RestaurantesService } from '../services/restaurantes.service';

@Component({
  selector: 'app-lista-restaurantes',
  templateUrl: './lista-restaurantes.component.html'
})
export class ListaRestaurantesComponent implements OnInit {

  tiposDeCozinha:Array<any>
  cep: string
  tipoDeCozinhaId: string
  restaurantesMaisProximos:Array<any>

  constructor(private route: ActivatedRoute,
              private router: Router,
              private tiposDeCozinhaService: TiposDeCozinhaService,
              private restaurantesService: RestaurantesService) {
  }

  ngOnInit() {
    this.tiposDeCozinhaService.todos().subscribe(data => {
      this.tiposDeCozinha = data;
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

  pedir(restaurante) {
    this.router.navigateByUrl(`/restaurantes/${this.cep}/restaurante/${restaurante.id}`);
  }
}
