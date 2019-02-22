import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  templateUrl: './principal.component.html'
})
export class PrincipalComponent {
  cep: string;

  constructor(private router: Router) { }

  buscar() {
    this.router.navigate(['/lista-restaurantes/', this.cep]);
  }
}
