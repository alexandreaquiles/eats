import { Component, OnInit } from '@angular/core';
import { TipoDeCozinhaService } from 'src/app/admin/tipo-de-cozinha/tipo-de-cozinha.service';

@Component({
  selector: 'app-tipo-de-cozinha-listagem',
  templateUrl: './tipo-de-cozinha-listagem.component.html'
})
export class TipoDeCozinhaListagemComponent implements OnInit {

  tiposDeCozinha: Array<any> = [];

  constructor(private tipoDeCozinhaService: TipoDeCozinhaService) { }

  ngOnInit() {
    this.tipoDeCozinhaService.all().subscribe(data => {
      this.tiposDeCozinha = data;
    });
  }

  remove(tipoDeCozinha) {
    this.tipoDeCozinhaService.remove(tipoDeCozinha).subscribe(result => {
      this.tiposDeCozinha = this.tiposDeCozinha.filter(t => t.id != tipoDeCozinha.id);
    }, error => console.error(error));
  }

}
