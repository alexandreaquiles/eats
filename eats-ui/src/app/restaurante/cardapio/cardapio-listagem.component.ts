import { Component, Input, OnInit } from '@angular/core';

import { CardapioService } from '../services/cardapio.service';
import { CategoriaDoCardapioService } from '../services/categoria-do-cardapio.service';

@Component({
  selector: 'app-cardapio-listagem',
  templateUrl: './cardapio-listagem.component.html'
})
export class CardapioListagemComponent implements OnInit {

  @Input() restaurante
  cardapio:any = {
    categorias: []
  }
  categoriaDoCardapioParaAdicionar:any = {}

  constructor(private cardapioService: CardapioService,
              private categoriaDoCardapioService: CategoriaDoCardapioService) {
  }

  ngOnInit() {
    this.cardapioService.doRestaurante(this.restaurante)
      .subscribe(cardapio => {
        this.cardapio = cardapio;
        this.categoriaDoCardapioParaAdicionar = { cardapio };
      });
  }

  adicionaCategoriaAoCardapio() {
    this.categoriaDoCardapioService.adicionaCategoriaAoCardapio(this.restaurante, this.cardapio, this.categoriaDoCardapioParaAdicionar)
      .subscribe(categoriaAdicionada => this.cardapio.categorias.push(categoriaAdicionada));
  }

}
