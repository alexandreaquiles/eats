import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from '@angular/router';

import { Observable } from 'rxjs';

import { TipoDeCozinhaService } from 'src/app/services/tipo-de-cozinha.service';
import { RestauranteService } from 'src/app/services/restaurante.service';
import { AvaliacoesService } from 'src/app/services/avaliacoes.service';

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
              private restaurantesService: RestauranteService,
              private avaliacoesService: AvaliacoesService) {
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

          let observableMaisProximos: Observable<any>;
          if (this.tipoDeCozinhaId) {
            observableMaisProximos = this.restaurantesService.maisProximosPorCepETipoDeCozinha(this.cep, this.tipoDeCozinhaId);
          } else {
            observableMaisProximos = this.restaurantesService.maisProximosPorCep(this.cep);
          }

          observableMaisProximos.subscribe(restaurantes => {
            this.restaurantesMaisProximos = restaurantes;
            this.mediaDeAvaliacoesParaCadaRestaurante();
          });
        }
      }
    );
  }

  mediaDeAvaliacoesParaCadaRestaurante() {
    this.restaurantesMaisProximos.forEach(restaurante => {
      this.avaliacoesService.mediaDasAvaliacoes(restaurante)
        .subscribe(media => restaurante.mediaAvaliacoes = media);
    });
  }

  /*
  mediaDeAvaliacoesDosRestaurantes() {
    this.avaliacoesService.mediaDasAvaliacoesDosRestaurantes(this.restaurantesMaisProximos)
      .subscribe(infoMedias => {
        infoMedias.forEach(infoMedia => {
          const restaurante = this.restaurantesMaisProximos.find(restaurante => restaurante.id === infoMedia.restauranteId);
          restaurante.mediaAvaliacoes = infoMedia.media;
        });

      });
  }
  */

  escolher(restaurante) {
    this.router.navigateByUrl(`/pedidos/${this.cep}/restaurante/${restaurante.id}`);
  }
}
