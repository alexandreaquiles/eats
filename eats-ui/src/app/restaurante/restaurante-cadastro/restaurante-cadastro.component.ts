import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { TiposDeCozinhaService } from '../../principal/services/tipos-de-cozinha.service';
import { RestauranteService } from '../services/restaurante.service';

@Component({
  selector: 'app-restaurante-cadastro',
  templateUrl: './restaurante-cadastro.component.html'
})
export class RestauranteCadastroComponent implements OnInit {

  restaurante:any = {
    tipoDeCozinha: { }
  }
  tiposDeCozinha:Array<any>

  constructor(
              private route: ActivatedRoute,
              private router: Router,
              private tiposDeCozinhaService: TiposDeCozinhaService,
              private restauranteService: RestauranteService) {
  }

  ngOnInit() {
    this.tiposDeCozinhaService.todos().subscribe(data => {
      this.tiposDeCozinha = data;
    });

    const id = this.route.snapshot.params.id;
      if (id) {
        this.restauranteService.porId(id).subscribe((restaurante:any) => {
          if (restaurante) {
            this.restaurante = restaurante;
            this.gotoEdit(restaurante.id)
          }
        });
      }
    }

  gotoEdit(id) {
    this.router.navigate(['/cadastro/restaurante/'+id]);
  }

  salvaRestaurante() {
    this.restauranteService.salva(this.restaurante).subscribe(result => {
      this.restaurante = result;
    }, error => console.error(error));
  }

}
