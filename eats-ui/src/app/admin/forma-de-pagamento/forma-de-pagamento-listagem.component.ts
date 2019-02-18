import { Component, OnInit } from '@angular/core';
import { FormaDePagamentoService } from 'src/app/admin/forma-de-pagamento/forma-de-pagamento.service';

@Component({
  selector: 'app-forma-de-pagamento-listagem',
  templateUrl: './forma-de-pagamento-listagem.component.html'
})
export class FormaDePagamentoListagemComponent implements OnInit {

  formasDePagamento: Array<any> = [];

  constructor(private formaDePagamentoService: FormaDePagamentoService) { }

  ngOnInit() {
    this.formaDePagamentoService.todas().subscribe(data => {
      this.formasDePagamento = data;
    });
  }

  remove(formaDePagamento) {
    this.formaDePagamentoService.remove(formaDePagamento).subscribe(result => {
      this.formasDePagamento = this.formasDePagamento.filter(f => f.id != formaDePagamento.id);
    }, error => console.error(error));
  }

}
