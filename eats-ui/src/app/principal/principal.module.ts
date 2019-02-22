import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PrincipalComponent } from './principal.component';
import { ListaRestaurantesComponent } from './lista-restaurantes/lista-restaurantes.component';

import { principalRoutes } from './principal.routes';
import { TiposDeCozinhaService } from './lista-restaurantes/tipos-de-cozinha.service';
import { RestaurantesMaisProximosService } from './lista-restaurantes/restaurantes-mais-proximos.service';

@NgModule({
  declarations: [
    PrincipalComponent,
    ListaRestaurantesComponent
  ],
  imports: [ CommonModule, FormsModule, principalRoutes ],
  exports: [ PrincipalComponent ],
  providers: [ TiposDeCozinhaService, RestaurantesMaisProximosService ]
})
export class PrincipalModule { }
