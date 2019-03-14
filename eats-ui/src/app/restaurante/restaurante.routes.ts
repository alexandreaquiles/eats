import { RouterModule, Routes } from '@angular/router';

import { RestauranteCadastroComponent } from './restaurante-cadastro/restaurante-cadastro.component';
import { HorariosDeFuncionamentoCadastroComponent } from './horarios-de-funcionamento/horarios-de-funcionamento-cadastro.component';
import { CategoriaDoCardapioCadastroComponent } from './cardapio/categoria-do-cardapio-cadastro.component';
import { ItemDoCardapioCadastroComponent } from './cardapio/item-do-cardapio-cadastro.component';

const routes: Routes = [
  {
    path: 'cadastro/restaurante',
    component: RestauranteCadastroComponent
  },
  {
    path: 'cadastro/restaurante/:id',
    component: RestauranteCadastroComponent
  },
  {
    path: 'cadastro/restaurante/:restauranteId/horarios-de-funcionamento',
    component: HorariosDeFuncionamentoCadastroComponent
  },
  {
    path: 'cadastro/restaurante/:restauranteId/horarios-de-funcionamento/:horarioId',
    component: HorariosDeFuncionamentoCadastroComponent
  },
  {
    path: 'cadastro/restaurante/:restauranteId/cardapio/:cardapioId/categoria/:categoriaId',
    component: CategoriaDoCardapioCadastroComponent
  },
  {
    path: 'cadastro/restaurante/:restauranteId/cardapio/:cardapioId/categoria/:categoriaId/item',
    component: ItemDoCardapioCadastroComponent
  },
  {
    path: 'cadastro/restaurante/:restauranteId/cardapio/:cardapioId/categoria/:categoriaId/item/:itemId',
    component: ItemDoCardapioCadastroComponent
  }
];

export const restauranteRoutes = RouterModule.forChild(routes);

