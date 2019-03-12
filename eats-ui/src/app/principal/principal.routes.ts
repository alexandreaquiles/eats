import { RouterModule, Routes } from '@angular/router';

import { PrincipalComponent } from './principal.component';
import { ListaRestaurantesComponent } from './lista-restaurantes/lista-restaurantes.component';
import { RestauranteComponent } from './restaurante/restaurante.component';

const routes: Routes = [
  {
    path: '',
    component: PrincipalComponent
  },
  {
    path: 'restaurantes/:cep',
    component: ListaRestaurantesComponent
  },
  {
    path: 'restaurantes/:cep/tipos-de-cozinha/:tipoDeCozinhaId',
    component: ListaRestaurantesComponent
  },
  {
    path: 'restaurantes/:cep/restaurante/:restauranteId',
    component: RestauranteComponent
  }
];

export const principalRoutes = RouterModule.forRoot(routes);
