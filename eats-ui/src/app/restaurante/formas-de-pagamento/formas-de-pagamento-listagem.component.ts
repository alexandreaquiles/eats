import { Component, OnInit, Input } from '@angular/core';

import { FormaDePagamentoService } from '../services/forma-de-pagamento.service';

@Component({
  selector: 'app-formas-de-pagamento-listagem',
  templateUrl: './formas-de-pagamento-listagem.component.html'
})
export class FormasDePagamentoListagemComponent implements OnInit {

  @Input() restaurante
  todasAsFormasDePagamento
  formasDePagamentoDoRestaurante
  formaDePagamentoParaAdicionar

  constructor(private formaDePagamentoService:FormaDePagamentoService) {
    formaDePagamentoService.todas()
      .subscribe(todasAsFormas => this.todasAsFormasDePagamento = todasAsFormas);
  }

  ngOnInit() {
    this.formaDePagamentoService.doRestaurante(this.restaurante)
      .subscribe(formasDePagamento => this.formasDePagamentoDoRestaurante = formasDePagamento);
  }

  adicionaFormaDePagamentoAoRestaurante() {
    if (this.formaDePagamentoParaAdicionar) {
      const jaTem = this.formasDePagamentoDoRestaurante.some(f => f.id == this.formaDePagamentoParaAdicionar.id);
      if (!jaTem) {
        this.formaDePagamentoService.adicionaAoRestaurante(this.formaDePagamentoParaAdicionar, this.restaurante)
        .subscribe(() => {
            this.formasDePagamentoDoRestaurante.push(this.formaDePagamentoParaAdicionar);
            this.formasDePagamentoDoRestaurante.sort((a,b) => a.nome.localeCompare(b.nome));
            this.formaDePagamentoParaAdicionar = {};
        });
      }
    }
  }

  remove(formaDePagamento) {
    this.formaDePagamentoService.remove(formaDePagamento, this.restaurante).subscribe(() => {
      this.formasDePagamentoDoRestaurante = this.formasDePagamentoDoRestaurante.filter(f => f != formaDePagamento);
    });
  }
}
