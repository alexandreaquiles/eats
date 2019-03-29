import { RouterModule, Routes } from '@angular/router';

import { RestauranteCadastroComponent } from './restaurante-cadastro/restaurante-cadastro.component';
import { CategoriaDoCardapioCadastroComponent } from './cardapio/categoria-do-cardapio-cadastro.component';
import { ItemDoCardapioCadastroComponent } from './cardapio/item-do-cardapio-cadastro.component';
import { PedidosPendentesComponent } from './pedidos-pendentes/pedidos-pendentes.component';

const routes: Routes = [
  {
    path: 'restaurantes',
    component: RestauranteCadastroComponent
  },
  {
    path: 'restaurantes/:id',
    component: RestauranteCadastroComponent
  },
  {
    path: 'restaurantes/:restauranteId/cardapio/:cardapioId/categoria/:categoriaId',
    component: CategoriaDoCardapioCadastroComponent
  },
  {
    path: 'restaurantes/:restauranteId/cardapio/:cardapioId/categoria/:categoriaId/item',
    component: ItemDoCardapioCadastroComponent
  },
  {
    path: 'restaurantes/:restauranteId/cardapio/:cardapioId/categoria/:categoriaId/item/:itemId',
    component: ItemDoCardapioCadastroComponent
  },
  {
    path: 'restaurantes/:restauranteId/pedidos/pendentes',
    component: PedidosPendentesComponent
  }
];

export const restauranteRoutes = RouterModule.forChild(routes);

