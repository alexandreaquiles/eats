import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { FormaDePagamentoService } from './forma-de-pagamento.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-forma-de-pagamento-edicao',
  templateUrl: './forma-de-pagamento-edicao.component.html'
})
export class FormaDePagamentoEdicaoComponent implements OnInit, OnDestroy {
  formaDePagamento: any = {};

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private formaDePagamentoService: FormaDePagamentoService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.formaDePagamentoService.get(id).subscribe((formaDePagamento: any) => {
          if (formaDePagamento) {
            this.formaDePagamento = formaDePagamento;
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
    this.router.navigate(['/admin/forma-de-pagamento/listar']);
  }

  save(form: NgForm) {
    this.formaDePagamentoService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(formaDePagamento) {
    this.formaDePagamentoService.remove(formaDePagamento).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }
}
