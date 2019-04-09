import { RouterModule, Routes } from '@angular/router';

import { PrincipalComponent } from './principal.component';
import { ListaRestaurantesComponent } from './lista-restaurantes/lista-restaurantes.component';
import { RestauranteComponent } from './restaurante/restaurante.component';
import { PagamentoPedidoComponent } from './pagamento/pagamento-pedido.component';
import { StatusPedidoComponent } from './status/status-pedido.component';

const routes: Routes = [
  {
    path: '',
    component: PrincipalComponent
  },
  {
    path: 'pedidos/:cep',
    component: ListaRestaurantesComponent
  },
  {
    path: 'pedidos/:cep/tipos-de-cozinha/:tipoDeCozinhaId',
    component: ListaRestaurantesComponent
  },
  {
    path: 'pedidos/:cep/restaurante/:restauranteId',
    component: RestauranteComponent
  },
  {
    path: 'pedidos/:pedidoId/pagamento',
    component: PagamentoPedidoComponent
  },
  {
    path: 'pedidos/:pedidoId/status',
    component: StatusPedidoComponent
  }
];

export const principalRoutes = RouterModule.forRoot(routes);
