import { RouterModule, Routes } from '@angular/router';

import { PrincipalComponent } from './principal.component';
import { ListaRestaurantesComponent } from './lista-restaurantes/lista-restaurantes.component';

const routes: Routes = [
  {
    path: '',
    component: PrincipalComponent
  },
  {
    path: 'lista-restaurantes/:cep',
    component: ListaRestaurantesComponent
  },
];

export const principalRoutes = RouterModule.forRoot(routes);
