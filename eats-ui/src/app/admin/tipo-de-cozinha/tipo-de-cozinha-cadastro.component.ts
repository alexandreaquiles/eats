import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { TipoDeCozinhaService } from '../services/tipo-de-cozinha.service';

@Component({
  selector: 'app-tipo-de-cozinha-cadastro',
  templateUrl: './tipo-de-cozinha-cadastro.component.html'
})
export class TipoDeCozinhaCadastroComponent implements OnInit {

  tipoDeCozinha: any = {};

  constructor(private route: ActivatedRoute,
              private router: Router,
              private tipoDeCozinhaService: TipoDeCozinhaService) {
  }

  ngOnInit() {
      const id = this.route.snapshot.params.id;
      if (id) {
        this.tipoDeCozinhaService.porId(id)
          .subscribe((tipoDeCozinha: any) => {
            if (tipoDeCozinha) {
              this.tipoDeCozinha = tipoDeCozinha;
            } else {
              this.gotoList();
            }
          });
      }
  }

  gotoList() {
    this.router.navigate(['/admin/tipo-de-cozinha']);
  }

  salva() {
    this.tipoDeCozinhaService.salva(this.tipoDeCozinha).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
