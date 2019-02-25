import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { FormaDePagamentoService } from '../services/forma-de-pagamento.service';

@Component({
  selector: 'app-forma-de-pagamento-edicao',
  templateUrl: './forma-de-pagamento-edicao.component.html'
})
export class FormaDePagamentoEdicaoComponent implements OnInit, OnDestroy {

  formaDePagamento: any = {};

  tiposDeFormaDePagamento: Array<any> = [];

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private formaDePagamentoService: FormaDePagamentoService) {
  }

  ngOnInit() {

    this.formaDePagamentoService.tipos().subscribe(data => {
      this.tiposDeFormaDePagamento = data;
    });

    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.formaDePagamentoService.porId(id).subscribe((formaDePagamento: any) => {
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

  salva() {
    console.log(this.formaDePagamento);
    //this.formaDePagamento.tipo = this.formaDePagamento.tipo.nome;
    this.formaDePagamentoService.salva(this.formaDePagamento).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
