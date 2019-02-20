import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';

import { PrincipalComponent } from './principal.component';
import { PromocoesComponent } from './promocoes/promocoes.component';
import { RestaurantesMaisProximosComponent } from './restaurantes-mais-proximos/restaurantes-mais-proximos.component';
import { TiposDeCozinhaComponent } from './tipos-de-cozinha/tipos-de-cozinha.component';
import { UltimosRestaurantesComponent } from './ultimos-restaurantes/ultimos-restaurantes.component';

import { principalRoutes } from './principal.routes';

@NgModule({
  declarations: [
    PrincipalComponent,
    PromocoesComponent,
    RestaurantesMaisProximosComponent,
    TiposDeCozinhaComponent,
    UltimosRestaurantesComponent
  ],
  imports: [ CommonModule, principalRoutes ],
  exports: [ PrincipalComponent ]
})
export class PrincipalModule { }
