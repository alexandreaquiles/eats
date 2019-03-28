import { RouterModule, Routes } from '@angular/router';

import { RestauranteCadastroComponent } from './restaurante-cadastro/restaurante-cadastro.component';
import { HorariosDeFuncionamentoCadastroComponent } from './horarios-de-funcionamento/horarios-de-funcionamento-cadastro.component';
import { CategoriaDoCardapioCadastroComponent } from './cardapio/categoria-do-cardapio-cadastro.component';
import { ItemDoCardapioCadastroComponent } from './cardapio/item-do-cardapio-cadastro.component';
import { ListaPedidosPendentesComponent } from './acompanhamento/lista-pedidos-pendentes.component';

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
    path: 'restaurantes/:restauranteId/horarios-de-funcionamento',
    component: HorariosDeFuncionamentoCadastroComponent
  },
  {
    path: 'restaurantes/:restauranteId/horarios-de-funcionamento/:horarioId',
    component: HorariosDeFuncionamentoCadastroComponent
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
    component: ListaPedidosPendentesComponent
  }
];

export const restauranteRoutes = RouterModule.forChild(routes);

