import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { FormaDePagamentoService } from '../services/forma-de-pagamento.service';

@Component({
  selector: 'app-forma-de-pagamento-cadastro',
  templateUrl: './forma-de-pagamento-cadastro.component.html'
})
export class FormaDePagamentoCadastroComponent implements OnInit {

  formaDePagamento: any = {};

  tiposDeFormaDePagamento: Array<any> = [];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private formaDePagamentoService: FormaDePagamentoService) {
  }

  ngOnInit() {
    this.formaDePagamentoService.tipos().subscribe(data => {
      this.tiposDeFormaDePagamento = data;
    });

    const id = this.route.snapshot.params.id;
    if (id) {
      this.formaDePagamentoService.porId(id).subscribe(formaDePagamento => this.formaDePagamento = formaDePagamento);
    }
  }

  salva() {
    this.formaDePagamentoService.salva(this.formaDePagamento).subscribe(() => this.router.navigate(['/admin/forma-de-pagamento']));
  }

}
