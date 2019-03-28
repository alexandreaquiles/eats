import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { RestauranteService } from '../services/restaurante.service';
import { CardapioService } from '../services/cardapio.service';
import { CategoriaDoCardapioService } from '../services/categoria-do-cardapio.service';
import { ItemDoCardapioService } from '../services/item-do-cardapio.service';

@Component({
  selector: 'app-item-do-cardapio-cadastro',
  templateUrl: './item-do-cardapio-cadastro.component.html'
})
export class ItemDoCardapioCadastroComponent implements OnInit {

  restaurante: any = {};
  cardapio: any = {};
  categoria: any = {};
  item: any = {};

  constructor(private route: ActivatedRoute,
              private router: Router,
              private restauranteService: RestauranteService,
              private cardapioService: CardapioService,
              private categoriaDoCardapioService: CategoriaDoCardapioService,
              private itemDoCardapioService: ItemDoCardapioService) {
  }

  ngOnInit() {
    const restauranteId = this.route.snapshot.params.restauranteId;
    this.restauranteService.porId(restauranteId)
      .subscribe(restaurante => this.restaurante = restaurante);

    const cardapioId = this.route.snapshot.params.cardapioId;
    this.cardapioService.porId(restauranteId, cardapioId)
      .subscribe(cardapio => this.cardapio = cardapio);

    const categoriaId = this.route.snapshot.params.categoriaId;
    this.categoriaDoCardapioService.porId(restauranteId, cardapioId, categoriaId)
      .subscribe(categoria => this.categoria = categoria);

    const itemId = this.route.snapshot.params.itemId;
    if (itemId) {
      this.itemDoCardapioService.porId(restauranteId, cardapioId, categoriaId, itemId)
      .subscribe(item => this.item = item);
    }
  }

  salva() {
    this.cardapio.restaurante = this.restaurante;
    this.categoria.cardapio = this.cardapio;
    this.item.categoria = this.categoria;
    this.itemDoCardapioService.salva(this.item).subscribe(() => {
      this.router.navigate(['/restaurantes', this.restaurante.id, 'cardapio', this.cardapio.id, 'categoria', this.categoria.id]);
    });
  }

}
