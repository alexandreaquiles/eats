import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { TipoDeCozinhaService } from 'src/app/services/tipo-de-cozinha.service';
import { RestauranteService } from '../../services/restaurante.service';

@Component({
  selector: 'app-restaurante-cadastro',
  templateUrl: './restaurante-cadastro.component.html'
})
export class RestauranteCadastroComponent implements OnInit {

  restaurante: any = {
    tipoDeCozinha: { }
  };

  tiposDeCozinha: Array<any>;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private tipoDeCozinhaService: TipoDeCozinhaService,
              private restauranteService: RestauranteService) {
  }

  ngOnInit() {
    this.tipoDeCozinhaService.todos().subscribe(data => {
      this.tiposDeCozinha = data;
    });

    const id = this.route.snapshot.params.id;
    if (id) {
      this.restauranteService.porId(id)
        .subscribe((restaurante: any) => {
          if (restaurante) {
            this.restaurante = restaurante;
            this.router.navigate([`/restaurantes/${restaurante.id}`]);
          }
        });
    }
  }

  salvaRestaurante() {
    this.restauranteService.salva(this.restaurante)
      .subscribe(restaurante => {
        if (!this.restaurante.id) {
          this.router.navigate([`/restaurantes/${restaurante.id}`]);
        }
      });
  }

}
