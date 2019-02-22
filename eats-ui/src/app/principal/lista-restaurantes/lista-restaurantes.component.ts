import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from '@angular/router';

import { TiposDeCozinhaService } from './tipos-de-cozinha.service';
import { RestaurantesMaisProximosService } from './restaurantes-mais-proximos.service';

@Component({
  selector: 'app-lista-restaurantes',
  templateUrl: './lista-restaurantes.component.html'
})
export class ListaRestaurantesComponent implements OnInit {

  tiposDeCozinha:Array<any>
  cep: string
  restaurantesMaisProximos:Array<any>

  constructor(private route: ActivatedRoute,
              private tiposDeCozinhaService: TiposDeCozinhaService,
              private restaurantesMaisProximosService: RestaurantesMaisProximosService) {
  }

  ngOnInit() {
    this.tiposDeCozinhaService.todos().subscribe(data => {
      this.tiposDeCozinha = data;
    });

    this.route.params.subscribe(params => {
      this.cep = params['cep'];
      if (this.cep) {
        this.restaurantesMaisProximosService.porCep(this.cep).subscribe((restaurantes: any) => {
            this.restaurantesMaisProximos = restaurantes;
        });
      }
    });
  }

  pedir(restaurante) {
    alert(restaurante.nome);
  }
}
