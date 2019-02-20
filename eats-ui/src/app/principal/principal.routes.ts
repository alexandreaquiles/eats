import { RouterModule, Routes } from '@angular/router';

import { PrincipalComponent } from './principal.component';

const routes: Routes = [
  {
    path: '',
    component: PrincipalComponent
  }
];

export const principalRoutes = RouterModule.forRoot(routes);
