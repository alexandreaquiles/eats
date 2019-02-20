import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { TipoDeCozinhaService } from './tipo-de-cozinha.service';

@Component({
  selector: 'app-tipo-de-cozinha-edicao',
  templateUrl: './tipo-de-cozinha-edicao.component.html'
})
export class TipoDeCozinhaEdicaoComponent implements OnInit, OnDestroy {

  tipoDeCozinha: any = {};

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private tipoDeCozinhaService: TipoDeCozinhaService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.tipoDeCozinhaService.porId(id).subscribe((tipoDeCozinha: any) => {
          if (tipoDeCozinha) {
            this.tipoDeCozinha = tipoDeCozinha;
          } else {
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/admin/tipo-de-cozinha/listar']);
  }

  salva() {
    this.tipoDeCozinhaService.salva(this.tipoDeCozinha).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
