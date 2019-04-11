import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { map } from 'rxjs/operators';

import { AuthenticationService } from 'src/app/services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuard implements CanActivate {

  constructor(private router: Router,
              private authenticationService: AuthenticationService,
              private toasterService: ToastrService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const role = route.data.role;
    if (role && this.authenticationService.hasRole(role)) {
      return true;
    }
    this.toasterService.error('Efetue o login para ter acesso.', 'Acesso negado');
    this.router.navigate(['/login']);
    return false;
  }
}
